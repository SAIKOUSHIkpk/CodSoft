import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class GradeCalculator extends JFrame {
    private JTextField[] marksFields;
    private JButton calculateButton;
    private JLabel totalMarksLabel, averagePercentageLabel, gradeLabel;

    public GradeCalculator() {
        setTitle("Student Grade Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        JPanel resultPanel = new JPanel(new GridLayout(3, 2, 5, 5));

        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Enter Marks"));
        resultPanel.setBorder(BorderFactory.createTitledBorder("Results"));

        marksFields = new JTextField[5];

        for (int i = 0; i < marksFields.length; i++) {
            JLabel subjectLabel = new JLabel("Subject " + (i + 1) + " Marks:");
            inputPanel.add(subjectLabel);
            marksFields[i] = new JTextField();
            inputPanel.add(marksFields[i]);
        }

        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new CalculateListener());

        resultPanel.add(new JLabel("Total Marks:"));
        totalMarksLabel = new JLabel("");
        resultPanel.add(totalMarksLabel);

        resultPanel.add(new JLabel("Average Percentage:"));
        averagePercentageLabel = new JLabel("");
        resultPanel.add(averagePercentageLabel);

        resultPanel.add(new JLabel("Grade:"));
        gradeLabel = new JLabel("");
        resultPanel.add(gradeLabel);

        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(calculateButton, BorderLayout.CENTER);
        mainPanel.add(resultPanel, BorderLayout.SOUTH);

        add(mainPanel);
        pack();
        setLocationRelativeTo(null); // Center the window
    }

    private class CalculateListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            int totalMarks = 0;
            int subjects = marksFields.length;

            for (int i = 0; i < subjects; i++) {
                try {
                    int marks = Integer.parseInt(marksFields[i].getText());
                    totalMarks += marks;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Please enter valid marks for all subjects.");
                    return;
                }
            }

            double averagePercentage = (double) totalMarks / subjects;
            DecimalFormat df = new DecimalFormat("#.##");
            String averagePercentageFormatted = df.format(averagePercentage);

            totalMarksLabel.setText(Integer.toString(totalMarks));
            averagePercentageLabel.setText(averagePercentageFormatted + "%");

            String grade;
            if (averagePercentage >= 90) {
                grade = "A";
            } else if (averagePercentage >= 80) {
                grade = "B";
            } else if (averagePercentage >= 70) {
                grade = "C";
            } else if (averagePercentage >= 60) {
                grade = "D";
            } else {
                grade = "F";
            }
            gradeLabel.setText(grade);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GradeCalculator calculator = new GradeCalculator();
                calculator.setVisible(true);
            }
        });
    }
}
