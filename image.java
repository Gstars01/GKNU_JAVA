import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class ImageViewer extends JFrame {
    private JLabel imageLabel;         // 이미지 출력용
    private JLabel fileNameLabel;      // 파일 이름 표시용
    private BufferedImage originalImage;
    private double scale = 1.0;

    public ImageViewer() {
        setTitle("이미지 뷰어");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container c = getContentPane();
        c.setLayout(new BorderLayout());

        JPanel toplayout = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton openButton = new JButton("이미지 선택");
        JButton deleteButton = new JButton("이미지 삭제");

        toplayout.add(openButton);
        toplayout.add(deleteButton);
        c.add(toplayout, BorderLayout.NORTH);

// 파일 이름 라벨과 이미지 라벨을 담을 패널 생성 (세로 배치)
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

// 파일 이름 라벨 왼쪽 정렬
        fileNameLabel = new JLabel("파일 이름: 없음");
        fileNameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

// 이미지 라벨 가운데 정렬
        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);
        imageLabel.setOpaque(true);
        imageLabel.setBackground(Color.LIGHT_GRAY);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

// 파일 이름 라벨과 이미지 라벨 사이 약간 공간 추가
        centerPanel.add(fileNameLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPanel.add(imageLabel);

        c.add(centerPanel, BorderLayout.CENTER);

        openButton.addActionListener(e -> openImage());
        deleteButton.addActionListener(e -> deleteImage());
        
        imageLabel.addMouseWheelListener(e -> {
            if (originalImage != null) {
                int notches = e.getWheelRotation();
                if (notches < 0) scale *= 1.1;
                else scale /= 1.1;
                ImageSizing();
            }
        });

        setVisible(true);
    }

    private void openImage() {
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                File file = chooser.getSelectedFile();
                originalImage = ImageIO.read(file);
                scale = 1.0;
                fileNameLabel.setText("파일 이름: " + file.getName());
                ImageSizing();
            } catch (Exception ex) {
                System.err.println("이미지 로딩 실패: " + ex.getMessage());
            }
        }
    }

    private void deleteImage() {
        originalImage = null;
        imageLabel.setIcon(null);
        fileNameLabel.setText("파일 이름: 없음");
    }

    private void ImageSizing() {
        if (originalImage != null) {
            int width = (int) (originalImage.getWidth() * scale);
            int height = (int) (originalImage.getHeight() * scale);
            Image scaled = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaled));
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ImageViewer());

    }
}
