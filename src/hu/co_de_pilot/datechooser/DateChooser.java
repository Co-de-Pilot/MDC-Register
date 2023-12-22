package hu.co_de_pilot.datechooser;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public final class DateChooser extends javax.swing.JPanel {

	private final String MONTH_HUNGARIAN[] = {"Január", "Február", "Március", "Április", "Május", "Június", "Július", "Augusztus", "Szeptember", "Október", "November", "December"};
	private JTextField textRefernce;
	private String dateFormat = "yyyy-MM-dd";
	private int MONTH = 1;
	private int YEAR = 2021;
	private int DAY = 1;
	private int STATUS = 1;   //  1 is day    2 is month  3 is year
	private int startYear;
	private SelectedDate selectedDate = new SelectedDate();
	private List<EventDateChooser> events;
	
	public DateChooser() {
		initComponents();
		execute();
	}
	
//	public void run() {
//		initComponents();
//		execute();
//	}
//	
    public JTextField getTextRefernce() {
        return textRefernce;
    }

    public void setTextRefernce(JTextField txt) {
    	this.textRefernce = txt;
    	this.textRefernce.setEditable(false);
    	this.textRefernce.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mouseClicked(MouseEvent me) {
    			if (textRefernce.isEnabled()) {
    				showPopup();
    			}
    		}
    	});
    	setText(false, 0);
    }
    
    public String getDateFormat() {
    	return dateFormat;
    }
    
    public void setDateFormat(String dateFormat) {
    	this.dateFormat = dateFormat;
    }
    
    public void showPopup(Component com, int x, int y) {
    	popup.show(com, x, y);
    }
    
    public void showPopup() {
    	popup.show(textRefernce, 0, textRefernce.getHeight());
    }
    
    public void hidePopup() {
    	popup.setVisible(false);
    }
    
    public void addEventDateChooser(EventDateChooser event) {
        events.add(event);
    }

    private void execute() {
        setForeground(Color.decode("#26a0da"));
        events = new ArrayList<>();
        popup.add(this);
        toDay(false);
    }

    private void setText(boolean runEvent, int act) {
        if (textRefernce != null) {
            try {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                Date date = df.parse(YEAR + "-" + MONTH + "-" + DAY);
                textRefernce.setText(new SimpleDateFormat(dateFormat).format(date));
            } catch (ParseException e) {
                System.err.println(e);
            }
        }
        if (runEvent) {
            runEvent(act);
        }
    }

    private void runEvent(int act) {
        SelectedAction action = new SelectedAction() {
            @Override
            public int getAction() {
                return act;
            }
        };
        for (EventDateChooser event : events) {
            event.dateSelected(action, selectedDate);
        }
    }

    private Event getEventDay(Dates dates) {
        return (MouseEvent evt, int num) -> {
            dates.clearSelected();
            dates.setSelected(num);
            DAY = num;
            selectedDate.setDay(DAY);
            selectedDate.setMonth(MONTH);
            selectedDate.setYear(YEAR);
            setText(true, 1);
            if (evt != null && evt.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(evt)) {
                popup.setVisible(false);
            }
        };
    }

    private Event getEventMonth() {
        return (MouseEvent evt, int num) -> {
            MONTH = num;
            selectedDate.setDay(DAY);
            selectedDate.setMonth(MONTH);
            selectedDate.setYear(YEAR);
            setText(true, 2);
            Dates d = new Dates();
            d.setForeground(getForeground());
            d.setEvent(getEventDay(d));
            d.showDate(MONTH, YEAR, selectedDate);
            if (slide.slideToDown(d)) {
                cmdMonth.setText(MONTH_HUNGARIAN[MONTH - 1]);
                cmdYear.setText(YEAR + "");
                STATUS = 1;
            }
        };
    }

    private Event getEventYear() {
        return (MouseEvent evt, int num) -> {
            YEAR = num;
            selectedDate.setDay(DAY);
            selectedDate.setMonth(MONTH);
            selectedDate.setYear(YEAR);
            setText(true, 3);
            Months d = new Months();
            d.setEvent(getEventMonth());
            if (slide.slideToDown(d)) {
                cmdMonth.setText(MONTH_HUNGARIAN[MONTH - 1]);
                cmdYear.setText(YEAR + "");
                STATUS = 2;
            }
        };
    }

    private void toDay(boolean runEvent) {
        Dates dates = new Dates();
        dates.setForeground(getForeground());
        dates.setEvent(getEventDay(dates));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String toDay = df.format(date);
        DAY = Integer.valueOf(toDay.split("-")[2]);
        MONTH = Integer.valueOf(toDay.split("-")[1]);
        YEAR = Integer.valueOf(toDay.split("-")[0]);
        selectedDate.setDay(DAY);
        selectedDate.setMonth(MONTH);
        selectedDate.setYear(YEAR);
        dates.showDate(MONTH, YEAR, selectedDate);
        slide.slideNon(dates);
        cmdMonth.setText(MONTH_HUNGARIAN[MONTH - 1]);
        cmdYear.setText(YEAR + "");
        setText(runEvent, 0);
    }

    public void toDay() {
        toDay(true);
    }

    private void setDateNext() {
        Dates dates = new Dates();
        dates.setForeground(getForeground());
        dates.setEvent(getEventDay(dates));
        dates.showDate(MONTH, YEAR, selectedDate);
        if (slide.slideToLeft(dates)) {
            cmdMonth.setText(MONTH_HUNGARIAN[MONTH - 1]);
            cmdYear.setText(YEAR + "");
        }
    }

    private void setDateBack() {
        Dates dates = new Dates();
        dates.setForeground(getForeground());
        dates.setEvent(getEventDay(dates));
        dates.showDate(MONTH, YEAR, selectedDate);
        if (slide.slideToRight(dates)) {
            cmdMonth.setText(MONTH_HUNGARIAN[MONTH - 1]);
            cmdYear.setText(YEAR + "");
        }
    }

    private void setYearNext() {
        Years years = new Years();
        years.setEvent(getEventYear());
        startYear = years.next(startYear);
        slide.slideToLeft(years);
    }

    private void setYearBack() {
        if (startYear >= 1000) {
            Years years = new Years();
            years.setEvent(getEventYear());
            startYear = years.back(startYear);
            slide.slideToLeft(years);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popup = new javax.swing.JPopupMenu(){
            @Override
            protected void paintComponent(Graphics grphcs) {
                grphcs.setColor(new Color(114, 113, 113));
                grphcs.fillRect(0, 0, getWidth(), getHeight());
                grphcs.setColor(Color.WHITE);
                grphcs.fillRect(1, 1, getWidth() - 2, getHeight() - 2);
            }
        };
        header = new javax.swing.JPanel();
        cmdForward = new hu.co_de_pilot.datechooser.Button();
        cmdClear = new hu.co_de_pilot.datechooser.Button();
        MY = new javax.swing.JLayeredPane();
        cmdMonth = new hu.co_de_pilot.datechooser.Button();
        lb = new javax.swing.JLabel();
        cmdYear = new hu.co_de_pilot.datechooser.Button();
        cmdHide = new hu.co_de_pilot.datechooser.Button();
        cmdPrevious = new hu.co_de_pilot.datechooser.Button();
        slide = new hu.co_de_pilot.datechooser.Slider();

        setBackground(new java.awt.Color(255, 255, 255));

        header.setBackground(new java.awt.Color(204, 93, 93));
        header.setMaximumSize(new java.awt.Dimension(262, 40));

        cmdForward.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmdForward.setToolTipText("Következő időszak");
        try {
        	Image gripen_forward = new BufferedImage(30, 30, BufferedImage.TYPE_INT_ARGB);
        	gripen_forward = ImageIO.read(new File("resources\\datechooser_icons\\gripen_forward.png"));
        	ImageIcon forwardicon = new ImageIcon(gripen_forward);
        	cmdForward.setIcon(forwardicon); // NOI18N
		} catch (IOException e) {
			e.printStackTrace();
		}        
        cmdForward.setFocusable(true);
        cmdForward.setPaintBackground(false);
        cmdForward.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdForwardActionPerformed(evt);
            }
        });

        cmdClear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmdClear.setToolTipText("Érték törlése");
        try {
        	Image clear = new BufferedImage(30, 30, BufferedImage.TYPE_INT_ARGB);
        	clear = ImageIO.read(new File("resources\\datechooser_icons\\clear_black.png"));
        	ImageIcon clearicon = new ImageIcon(clear);
        	cmdClear.setIcon(clearicon); // NOI18N
        } catch (IOException e) {
        	e.printStackTrace();
        }
        cmdClear.setFocusable(true);
        cmdClear.setPaintBackground(false);
        cmdClear.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		getTextRefernce().setText("");
        	}
        });
        
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0);
        flowLayout1.setAlignOnBaseline(true);
        MY.setLayout(flowLayout1);

        cmdYear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmdYear.setToolTipText("Év beállítása");
        cmdYear.setForeground(new java.awt.Color(255, 255, 255));
//        cmdYear.setText("2018");
        cmdYear.setFocusPainted(false);
        cmdYear.setFont(new java.awt.Font("Kh Content", 0, 14)); // NOI18N
        cmdYear.setPaintBackground(false);
        cmdYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdYearActionPerformed(evt);
            }
        });
        MY.add(cmdYear);

        lb.setForeground(new java.awt.Color(255, 255, 255));
        lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb.setText("-");
        MY.add(lb);
        
        cmdMonth.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmdMonth.setToolTipText("Hónap beállítása");
        cmdMonth.setForeground(new java.awt.Color(255, 255, 255));
//        cmdMonth.setText("Január");
        cmdMonth.setFocusPainted(false);
        cmdMonth.setFont(new java.awt.Font("Kh Content", 0, 14)); // NOI18N
        cmdMonth.setPaintBackground(false);
        cmdMonth.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		cmdMonthActionPerformed(evt);
        	}
        });
        MY.add(cmdMonth);
        
        cmdHide.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmdHide.setToolTipText("Ablak bezárása");
        try {
        	Image close = new BufferedImage(30, 30, BufferedImage.TYPE_INT_ARGB);
        	close = ImageIO.read(new File("resources\\datechooser_icons\\close_black.png"));
        	ImageIcon closeicon = new ImageIcon(close);
        	cmdHide.setIcon(closeicon); // NOI18N
        } catch (IOException e) {
        	e.printStackTrace();
        }
        cmdHide.setFocusable(true);
        cmdHide.setPaintBackground(false);
        cmdHide.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	hidePopup();
            }
        });
        
        cmdPrevious.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmdPrevious.setToolTipText("Előző időszak");
        try {
        	Image gripen_previous = new BufferedImage(30, 30, BufferedImage.TYPE_INT_ARGB);
        	gripen_previous = ImageIO.read(new File("resources\\datechooser_icons\\gripen_previous.png"));
        	ImageIcon previousicon = new ImageIcon(gripen_previous);
        	cmdPrevious.setIcon(previousicon); // NOI18N
        } catch (IOException e) {
        	e.printStackTrace();
        }
        cmdPrevious.setFocusable(true);
        cmdPrevious.setPaintBackground(false);
        cmdPrevious.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		cmdPreviousActionPerformed(evt);
        	}
        });
        cmdPrevious.addKeyListener(new java.awt.event.KeyAdapter() {
        	public void keyPressed(java.awt.event.KeyEvent evt) {
        		cmdPreviousKeyPressed(evt);
        	}
        });
        
        

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmdPrevious, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdForward, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MY, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdClear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdHide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmdPrevious, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmdForward, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(MY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmdHide, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        slide.setLayout(new javax.swing.BoxLayout(slide, javax.swing.BoxLayout.LINE_AXIS));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(slide, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(slide, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmdPreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdPreviousActionPerformed
        if (STATUS == 1) {   //  Date
            if (MONTH == 1) {
                MONTH = 12;
                YEAR--;
            } else {
                MONTH--;
            }
            setDateBack();
        } else if (STATUS == 3) {    //  Year
            setYearBack();
        } else {
            if (YEAR >= 1000) {
                YEAR--;
                Months months = new Months();
                months.setEvent(getEventMonth());
                slide.slideToLeft(months);
                cmdYear.setText(YEAR + "");
            }
        }
    }//GEN-LAST:event_cmdPreviousActionPerformed

    private void cmdForwardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdForwardActionPerformed
        if (STATUS == 1) {   //  Date
            if (MONTH == 12) {
                MONTH = 1;
                YEAR++;
            } else {
                MONTH++;
            }
            setDateNext();
        } else if (STATUS == 3) {    //  Year
            setYearNext();
        } else {
            YEAR++;
            Months months = new Months();
            months.setEvent(getEventMonth());
            slide.slideToLeft(months);
            cmdYear.setText(YEAR + "");
        }
    }//GEN-LAST:event_cmdForwardActionPerformed

    private void cmdMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdMonthActionPerformed
        if (STATUS != 2) {
            STATUS = 2;
            Months months = new Months();
            months.setEvent(getEventMonth());
            slide.slideToDown(months);
        } else {
            Dates dates = new Dates();
            dates.setForeground(getForeground());
            dates.setEvent(getEventDay(dates));
            dates.showDate(MONTH, YEAR, selectedDate);
            slide.slideToDown(dates);
            STATUS = 1;
        }
    }//GEN-LAST:event_cmdMonthActionPerformed

    private void cmdYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdYearActionPerformed
        if (STATUS != 3) {
            STATUS = 3;
            Years years = new Years();
            years.setEvent(getEventYear());
            startYear = years.showYear(YEAR);
            slide.slideToDown(years);
        } else {
            Dates dates = new Dates();
            dates.setForeground(getForeground());
            dates.setEvent(getEventDay(dates));
            dates.showDate(MONTH, YEAR, selectedDate);
            slide.slideToDown(dates);
            STATUS = 1;
        }
    }//GEN-LAST:event_cmdYearActionPerformed

    private void cmdPreviousKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmdPreviousKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            Component com = slide.getComponent(0);
            if (com instanceof Dates) {
                Dates d = (Dates) com;
                d.up();
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            Component com = slide.getComponent(0);
            if (com instanceof Dates) {
                Dates d = (Dates) com;
                d.down();
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            Component com = slide.getComponent(0);
            if (com instanceof Dates) {
                Dates d = (Dates) com;
                d.back();
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            Component com = slide.getComponent(0);
            if (com instanceof Dates) {
                Dates d = (Dates) com;
                d.next();
            }
        }
    }//GEN-LAST:event_cmdPreviousKeyPressed

    public void setSelectedDate(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); //CHANGE!!!
//        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy"); //CHANGE!!!
        String d = df.format(date);
        DAY = Integer.valueOf(d.split("-")[2]);
        MONTH = Integer.valueOf(d.split("-")[1]);
        YEAR = Integer.valueOf(d.split("-")[0]);
//        DAY = Integer.valueOf(d.split("-")[0]);
//        MONTH = Integer.valueOf(d.split("-")[1]);
//        YEAR = Integer.valueOf(d.split("-")[2]);
        selectedDate.setDay(DAY);
        selectedDate.setMonth(MONTH);
        selectedDate.setYear(YEAR);
        Dates dates = new Dates();
        dates.setForeground(getForeground());
        dates.setEvent(getEventDay(dates));
        dates.setSelected(DAY);
        dates.showDate(MONTH, YEAR, selectedDate);
        slide.slideNon(dates);
        cmdMonth.setText(MONTH_HUNGARIAN[MONTH - 1]);
        cmdYear.setText(YEAR + "");
        setText(true, 0);
        STATUS = 1;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane MY;
    private hu.co_de_pilot.datechooser.Button cmdForward;
    private hu.co_de_pilot.datechooser.Button cmdMonth;
    private hu.co_de_pilot.datechooser.Button cmdPrevious;
    private hu.co_de_pilot.datechooser.Button cmdYear;
    private hu.co_de_pilot.datechooser.Button cmdHide;
    private hu.co_de_pilot.datechooser.Button cmdClear;
    private javax.swing.JPanel header;
    private javax.swing.JLabel lb;
    private javax.swing.JPopupMenu popup;
    private hu.co_de_pilot.datechooser.Slider slide;
    // End of variables declaration//GEN-END:variables

    public SelectedDate getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(SelectedDate selectedDate) {
        this.selectedDate = selectedDate;
        DAY = selectedDate.getDay();
        MONTH = selectedDate.getMonth();
        YEAR = selectedDate.getYear();
        Dates dates = new Dates();
        dates.setForeground(getForeground());
        dates.setEvent(getEventDay(dates));
        dates.setSelected(DAY);
        dates.showDate(MONTH, YEAR, selectedDate);
        slide.slideNon(dates);
        cmdMonth.setText(MONTH_HUNGARIAN[MONTH - 1]);
        cmdYear.setText(YEAR + "");
        setText(true, 0);
        STATUS = 1;
    }

    @Override
    public void setForeground(Color color) {
        super.setForeground(color);
        if (header != null) {
            header.setBackground(color);
            toDay(false);
        }
    }
}
