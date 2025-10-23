// TextAnimator.java
package com.rpg.ui;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.TextArea;
import javafx.util.Duration;

public class TextAnimator {
    private Timeline timeline;
    private String fullText;
    private TextArea textArea;
    private int currentIndex;
    private Runnable onFinished;
    
    public TextAnimator(TextArea textArea) {
        this.textArea = textArea;
    }
    
    public void animateText(String text, int speedMs, Runnable onFinished) {
        this.fullText = text;
        this.currentIndex = 0;
        this.onFinished = onFinished;
        
        // Limpiar animación anterior
        if (timeline != null) {
            timeline.stop();
        }
        
        textArea.setText("");
        
        timeline = new Timeline(new KeyFrame(Duration.millis(speedMs), e -> {
            if (currentIndex < fullText.length()) {
                textArea.appendText(String.valueOf(fullText.charAt(currentIndex)));
                currentIndex++;
            } else {
                timeline.stop();
                if (onFinished != null) {
                    onFinished.run();
                }
            }
        }));
        
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    
    public void skipAnimation() {
        if (timeline != null && timeline.getStatus() == Animation.Status.RUNNING) {
            timeline.stop();
            textArea.setText(fullText);
            if (onFinished != null) {
                onFinished.run();
            }
        }
    }
    
    public boolean isAnimating() {
        return timeline != null && timeline.getStatus() == Animation.Status.RUNNING;
    }
}