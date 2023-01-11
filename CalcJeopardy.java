public class CalcJeopardy {
    
    static int numPlayers = 2;
    static VectorQuestions vectQ = new VectorQuestions();
    static DiffQuestions diffQ = new DiffQuestions();
    static CurveQuestions curveQ = new CurveQuestions();
    static LHopitalQuestions hopQ = new LHopitalQuestions();
    static IntegrationQuestions intQ = new IntegrationQuestions();
    
    public static void main(String[] args) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AskQuestion dialog = new AskQuestion(new javax.swing.JFrame(), true, numPlayers, vectQ, diffQ, curveQ, hopQ, intQ);
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
