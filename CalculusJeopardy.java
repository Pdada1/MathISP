public class CalculusJeopardy {

    static int player1Score = 0;
    static int player2Score = 0;
    
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AskQuestion dialog = new AskQuestion(new javax.swing.JFrame(), true, player1Score, player2Score);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    
}
