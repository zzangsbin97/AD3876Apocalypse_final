package gameMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel {

    private JLabel timerLabel;  // 추가: 시간을 표시할 레이블
    private Timer timer;  // 추가: 시간 업데이트를 위한 타이머

    public GamePanel() {
        initPanel();
        initTimer();
    }

    private void initPanel() {
        setLayout(new FlowLayout(FlowLayout.RIGHT));  // 추가: 레이아웃을 오른쪽 정렬로 설정

        timerLabel = new JLabel("Time: 60");  // 추가: 초기 시간 표시
        add(timerLabel);
    }

    private void initTimer() {
        int delay = 1000;  // 1초마다 업데이트
        timer = new Timer(delay, new ActionListener() {
            int elapsedTime = 0;  // 경과 시간 초기화

            @Override
            public void actionPerformed(ActionEvent e) {
                elapsedTime += 1000;  // 1초씩 증가
                int remainingTime = 60000 - elapsedTime;  // 남은 시간 계산 (60초 - 경과 시간)

                if (remainingTime >= 0) {
                    // 남은 시간이 0보다 크면 시간을 업데이트하여 레이블에 표시
                    int seconds = remainingTime / 1000;
                    timerLabel.setText("Time: " + seconds);
                } else {
                    // 남은 시간이 0 이하면 타이머를 멈추고 메시지를 표시
                    timer.stop();
                    timerLabel.setText("Time's up!");
                }
            }
        });

        timer.start();  // 타이머 시작
    }
    
    public static void main(String[] args) {
    	
    	new GamePanel();
    }
}

