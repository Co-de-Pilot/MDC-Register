package hu.co_de_pilot.datechooser;

public final class Months extends javax.swing.JPanel {

    private Event event;
    private int m;

    public Months() {
        initComponents();
    }

    private void addEvent() {
        for (int i = 0; i < getComponentCount(); i++) {
            ((Button) getComponent(i)).setEvent(event);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmd1 = new hu.co_de_pilot.datechooser.Button();
        cmd2 = new hu.co_de_pilot.datechooser.Button();
        cmd3 = new hu.co_de_pilot.datechooser.Button();
        cmd4 = new hu.co_de_pilot.datechooser.Button();
        cmd5 = new hu.co_de_pilot.datechooser.Button();
        cmd6 = new hu.co_de_pilot.datechooser.Button();
        cmd7 = new hu.co_de_pilot.datechooser.Button();
        cmd8 = new hu.co_de_pilot.datechooser.Button();
        cmd9 = new hu.co_de_pilot.datechooser.Button();
        cmd10 = new hu.co_de_pilot.datechooser.Button();
        cmd11 = new hu.co_de_pilot.datechooser.Button();
        cmd12 = new hu.co_de_pilot.datechooser.Button();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.GridLayout(4, 4));

        cmd1.setBackground(new java.awt.Color(255, 255, 255));
        cmd1.setForeground(new java.awt.Color(75, 75, 75));
        cmd1.setText("Január");
        cmd1.setName("1"); // NOI18N
        cmd1.setOpaque(true);
        add(cmd1);

        cmd2.setBackground(new java.awt.Color(255, 255, 255));
        cmd2.setForeground(new java.awt.Color(75, 75, 75));
        cmd2.setText("Február");
        cmd2.setName("2"); // NOI18N
        cmd2.setOpaque(true);
        add(cmd2);

        cmd3.setBackground(new java.awt.Color(255, 255, 255));
        cmd3.setForeground(new java.awt.Color(75, 75, 75));
        cmd3.setText("Március");
        cmd3.setName("3"); // NOI18N
        cmd3.setOpaque(true);
        add(cmd3);

        cmd4.setBackground(new java.awt.Color(255, 255, 255));
        cmd4.setForeground(new java.awt.Color(75, 75, 75));
        cmd4.setText("Április");
        cmd4.setName("4"); // NOI18N
        cmd4.setOpaque(true);
        add(cmd4);

        cmd5.setBackground(new java.awt.Color(255, 255, 255));
        cmd5.setForeground(new java.awt.Color(75, 75, 75));
        cmd5.setText("Május");
        cmd5.setName("5"); // NOI18N
        cmd5.setOpaque(true);
        add(cmd5);

        cmd6.setBackground(new java.awt.Color(255, 255, 255));
        cmd6.setForeground(new java.awt.Color(75, 75, 75));
        cmd6.setText("Június");
        cmd6.setName("6"); // NOI18N
        cmd6.setOpaque(true);
        add(cmd6);

        cmd7.setBackground(new java.awt.Color(255, 255, 255));
        cmd7.setForeground(new java.awt.Color(75, 75, 75));
        cmd7.setText("Július");
        cmd7.setName("7"); // NOI18N
        cmd7.setOpaque(true);
        add(cmd7);

        cmd8.setBackground(new java.awt.Color(255, 255, 255));
        cmd8.setForeground(new java.awt.Color(75, 75, 75));
        cmd8.setText("Augusztus");
        cmd8.setName("8"); // NOI18N
        cmd8.setOpaque(true);
        add(cmd8);

        cmd9.setBackground(new java.awt.Color(255, 255, 255));
        cmd9.setForeground(new java.awt.Color(75, 75, 75));
        cmd9.setText("Szeptember");
        cmd9.setName("9"); // NOI18N
        cmd9.setOpaque(true);
        add(cmd9);

        cmd10.setBackground(new java.awt.Color(255, 255, 255));
        cmd10.setForeground(new java.awt.Color(75, 75, 75));
        cmd10.setText("Október");
        cmd10.setName("10"); // NOI18N
        cmd10.setOpaque(true);
        add(cmd10);

        cmd11.setBackground(new java.awt.Color(255, 255, 255));
        cmd11.setForeground(new java.awt.Color(75, 75, 75));
        cmd11.setText("November");
        cmd11.setName("11"); // NOI18N
        cmd11.setOpaque(true);
        add(cmd11);

        cmd12.setBackground(new java.awt.Color(255, 255, 255));
        cmd12.setForeground(new java.awt.Color(75, 75, 75));
        cmd12.setText("December");
        cmd12.setName("12"); // NOI18N
        cmd12.setOpaque(true);
        add(cmd12);
    }// </editor-fold>//GEN-END:initComponents

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
        addEvent();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private hu.co_de_pilot.datechooser.Button cmd1;
    private hu.co_de_pilot.datechooser.Button cmd10;
    private hu.co_de_pilot.datechooser.Button cmd11;
    private hu.co_de_pilot.datechooser.Button cmd12;
    private hu.co_de_pilot.datechooser.Button cmd2;
    private hu.co_de_pilot.datechooser.Button cmd3;
    private hu.co_de_pilot.datechooser.Button cmd4;
    private hu.co_de_pilot.datechooser.Button cmd5;
    private hu.co_de_pilot.datechooser.Button cmd6;
    private hu.co_de_pilot.datechooser.Button cmd7;
    private hu.co_de_pilot.datechooser.Button cmd8;
    private hu.co_de_pilot.datechooser.Button cmd9;
    // End of variables declaration//GEN-END:variables

}
