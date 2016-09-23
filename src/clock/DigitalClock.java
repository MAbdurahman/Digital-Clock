/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clock;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;

/**
 * The DigitalClock Class is a simple application that displays the hour, minute,
 * and second of the day.  It also displays the day of the week, the date, and the
 * month.
 * @author:  MAbdurrahman
 * @date:  2 February 2016
 */
public class DigitalClock extends javax.swing.JFrame implements Runnable {
    //Instance Variables
    private final Thread clockThread;
    private Calendar calendar;
    private int month;
    private int day;
    private int dateOfMonth;
    private int hour;
    private int minute;
    private int second;
    private int time;

    /**
     * DigitalClock Constructor - creates an instance of the DigitalClock
     */
    @SuppressWarnings({"OverridableMethodCallInConstructor", 
                        "CallToThreadStartDuringObjectConstruction"})
    public DigitalClock() {
        initComponents();
        getContentPane().setBackground(Color.decode("#5E5856"));
        setLocation(400, 200);
        setTitle("Digital Clock");
        Image iconImage = Toolkit.getDefaultToolkit().getImage(DigitalClock.
                                        class.getResource("/images/digitalClock.png"));
        setIconImage(iconImage);
        setResizable(false);
        
        clockThread = new Thread(this);
        clockThread.start();
    
    }//end of the DigitalClock Constructor
    /**
     * run Method - Overrides the run Method for the Runnable Interface, and defines it to respond 
     * to the running of the clockThread, which runs the digitalClock.
     * @param Void
     */
    @Override
    @SuppressWarnings("SleepWhileInLoop")
    public void run() {
        try {
            while (true) {
                calendar = new GregorianCalendar();
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_WEEK);
                dateOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                hour = calendar.get(Calendar.HOUR_OF_DAY);
                minute = calendar.get(Calendar.MINUTE);
                second = calendar.get(Calendar.SECOND);
                time = calendar.get(Calendar.AM_PM);

                if ((minute < 10) && (second < 10)) {
                    clockLabel.setText("" + getHour(hour) + ":0" + minute + ":0" + second + " " + getAM_PM(time));

                }
                if ((minute >= 10) && (second < 10)) {
                    clockLabel.setText("" + getHour(hour) + ":" + minute + ":0" + second + " " + getAM_PM(time));

                }
                if ((minute >= 10) && (second >= 10)) {
                    clockLabel.setText("" + getHour(hour) + ":" + minute + ":" + second + " " + getAM_PM(time));

                }
                dateLabel.setText("" + getDayOfWeek(day) + ", " + dateOfMonth + " " + getMonth(month));
                Thread.sleep(1000);
            }
   
        } catch (InterruptedException ie) {
                String message = ie.getMessage();
                JOptionPane.showMessageDialog(rootPane, message);
        }
                    
    }//end of the run Method
    /**
     * getDayOfWeek Method - Gets the day of week
     * @param Int - the day of the week is expressed as an integer from 1 to 7
     * @return String - Returns the day of week as a String from Sunday to Saturday
     */
    private String getDayOfWeek(int day) {
        String today;
        switch (day) {
            case 1:
                today = "Sunday";
                break;
            case 2:
                today = "Monday";
                break;
            case 3: 
                today = "Tuesday";
                break;
            case 4:
                today = "Wednesday";
                break;
            case 5:
                today = "Thursday";
                break;
            case 6:
                today = "Friday";
                break;
            case 7:
                today = "Saturday";
                break;
            default: 
                today = "today";
                break;
        }
        return today;
        
    }//end of the getDayOfWeek Method
    /**
     * getMonth Method - Gets the month of the year
     * @param Int - the month of year is expressed an integer from 0 to 11
     * @return String - Returns the month as a String from January to December
     */
    private String getMonth(int month) {
        String thisMonth;
        switch (month) {
            case 0:
                thisMonth = "January";
                break;
            case 1:
                thisMonth = "February";
                break;
            case 2:
                thisMonth = "March";
                break;
            case 3:
                thisMonth = "April";
                break;
            case 4:
                thisMonth = "May";
                break;
            case 5:
                thisMonth = "June";
                break;
            case 6:
                thisMonth = "July";
                break;
            case 7:
                thisMonth = "August";
                break;
            case 8:
                thisMonth = "September";
                break;
            case 9:
                thisMonth = "October";
                break;
            case 10:
                thisMonth = "November";
                break;
            case 11:
                thisMonth = "December";
                break;
            default:
                thisMonth = "thisMonth";
                break;
        }
        
        return thisMonth;
        
    }//end of the getMonth Method
    /**
     * getHour Method - Get the hour of the day
     * @param Int - the hour of day is expressed as an integer from 0 to 23
     * @return String - Returns the hour of day as a String from 1 to 12.
     */
    private String getHour(int theHour) {
        String thisHour;
        switch (theHour) {
            case 0:
            case 12:
                thisHour = "12";
                break;
            case 1:
            case 13:
                thisHour = "1";
                break;
            case 2:
            case 14:
                thisHour = "2";
                break;
            case 3:
            case 15:
                thisHour = "3";
                break;
            case 4:
            case 16:
                thisHour = "4";
                break;
            case 5:
            case 17:
                thisHour = "5";
                break;
            case 6:
            case 18:
                thisHour = "6";
                break;
            case 7:
            case 19:
                thisHour = "7";
                break;
            case 8:
            case 20:
                thisHour = "8";
                break;
            case 9:
            case 21:
                thisHour = "9";
                break;
            case 10:
            case 22:
                thisHour = "10";
                break;
            case 11:
            case 23:
                thisHour = "11";
                break;
            default:
                thisHour = "??";
                break;
               
        }
        return thisHour;
        
    }//end of the getHour Method
    /**
     * getAM_PM Method - Gets the before noon (Ante Meridiem - am) and the afternoon
     * (Post Meridiem - pm) time of the day
     * @param Int - the time of day is expressed as a '1' or a '0'.
     * @return String - Returns the String "am" for before noon, or the String "pm"
     * for after noon.
     */
    private String getAM_PM(int timeOfDay) {
        String AM_PM;
        switch (timeOfDay) {
            case 0:
                AM_PM = "am";
                break;
            case 1:
                AM_PM = "pm";
                break;
            default:
                AM_PM = "Unavailable";
                break;           
        }
        return AM_PM;
        
    }//end of the getAM_PM Method
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        clockLabel = new javax.swing.JLabel();
        dateLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 51, 51));
        setForeground(new java.awt.Color(102, 102, 102));
        setName("frame"); // NOI18N

        mainPanel.setBackground(Color.decode("#5E5856")
        );
        mainPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 102), new java.awt.Color(153, 153, 153), new java.awt.Color(102, 102, 102), new java.awt.Color(153, 153, 153)));

        clockLabel.setBackground(Color.decode("#5E5856")
        );
        clockLabel.setFont(new java.awt.Font("Bookman Old Style", 0, 36)); // NOI18N
        clockLabel.setForeground(new java.awt.Color(255, 255, 255));
        clockLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        clockLabel.setText("8:05");
        clockLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        dateLabel.setBackground(Color.decode("#5E5856")
        );
        dateLabel.setFont(new java.awt.Font("Bookman Old Style", 0, 18)); // NOI18N
        dateLabel.setForeground(new java.awt.Color(255, 255, 255));
        dateLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dateLabel.setText("Monday, 1 February");
        dateLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(clockLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(dateLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(clockLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(209, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        

    /**
     * main Method - Contains the command line arguments
     * @param String[] - the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DigitalClock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DigitalClock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DigitalClock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DigitalClock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new DigitalClock().setVisible(true);
        });
    }//end of the main Method

    // Variables declaration - do not modify                     
    private javax.swing.JLabel clockLabel;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JPanel mainPanel;
    // End of variables declaration                   
}//end of the DigitalClock Class
