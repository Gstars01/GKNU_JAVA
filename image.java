import javax.swing.*;
import java.awt.*;
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

        //패널 생성 ( 플로우 레이아웃)(최상단)
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

        // 파일 이름 라벨과 이미지 라벨 사이 약간 공간 추가
        centerPanel.add(fileNameLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPanel.add(imageLabel);
        c.add(centerPanel, BorderLayout.CENTER);

        //액션 리스너 (람다함수)
        openButton.addActionListener(e-> openImage()); //람다함수는 매개변수 -> 실행문 구조이지만 여기서는 매개변수를 사용하지 않기때문에 아무 변수나 집어넣음
        deleteButton.addActionListener(e -> deleteImage());

        //마우스 휠 움직임 감지
        imageLabel.addMouseWheelListener(e -> {
            if (originalImage != null) {
                int notches = e.getWheelRotation();
                if (notches < 0) scale *= 1.1;
                else scale /= 1.1;
                //이미지 사이즈 변경클래스 호출
                ImageSizing();
            }
        });

        setVisible(true);
    }

    //이미지 열기 클래스
    private void openImage() {
        JFileChooser chooser = new JFileChooser();//파일 선택기 호출
        int result = chooser.showOpenDialog(this);  //파일을 선택하면 결과가 result에 저장
        if (result == JFileChooser.APPROVE_OPTION) {    //열기 선택시
            //예외처리
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

    //이미지 닫기 클래스
    private void deleteImage() {
        originalImage = null;
        imageLabel.setIcon(null);
        fileNameLabel.setText("파일 이름: 없음");
    }

    //이미지 사이즈 변경 클래스
    private void ImageSizing() {
        // 원본 이미지가 존재할 때만 실행
        if (originalImage != null) {
            // 스케일 비율에 따라 너비와 높이 계산
            int width = (int) (originalImage.getWidth() * scale);
            int height = (int) (originalImage.getHeight() * scale);
            // 이미지 크기 조정 (부드럽게 스케일링)
            Image scaled = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaled));
        }
    }



    public static void main(String[] args) {
        ImageViewer view = new ImageViewer();

    }
}
