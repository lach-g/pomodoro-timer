package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;


public class Controller implements Initializable
{
    @FXML
    private Button startButton;

    @FXML
    private Button pauseButton;

    @FXML
    private TextField studyTimeField;

    @FXML
    private TextField breakTimeField;

    @FXML
    private TextField iterationsTimeField;

    @FXML
    private Button enterButton;

    @FXML
    private Label timeLeftLabel;

    @FXML
    private Label finishTimeLabel;

    @FXML
    private TextField lunchTimeField;

    int studyNum;
    int countdown;

    /* Timer setup */
    Timer timer = new Timer();
    TimerTask task = new TimerTask()
    {
        public void run()
        {
            while(countdown > 0)
            {
                countdown = countdown - 1;
                System.out.println(countdown);

            }

        }
    };





    /** Initialize the controller class. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        finishTimeLabel.setText(getCurrentTimeString());

    }

    public void onStartButtonPressed(javafx.scene.input.MouseEvent mouseEvent)
    {
        System.out.println("Start button pressed");

        countdown = studyNum;

        timer.schedule(task, 0, 1000);

    }

    public void decrementCountdown()
    {
        countdown = countdown - 1;
    }

    public void updateTimeLeft()
    {
        timeLeftLabel.setText(getTimeLeftString(countdown));
    }


    public void onPauseButtonPressed(MouseEvent mouseEvent)
    {
        System.out.println("Pause button pressed");
    }

    public void onEnterButtonPressed(MouseEvent mouseEvent)
    {
        // TODO: Check valid input

        /* Check inputs. */
        String studyTime = studyTimeField.getText();
        String breakTime = breakTimeField.getText();
        String iterations = iterationsTimeField.getText();
        String lunchTime = lunchTimeField.getText();

        System.out.println("Study for: " + studyTime + "\nBreak for: " + breakTime + "\nLunch length: " + lunchTime + "\nIterations: " + iterations);

        if(studyTime != null && breakTime != null && iterations != null)
        {
            /* To ints */
            studyNum = Integer.parseInt(studyTime);
            int breakNum = Integer.parseInt(breakTime);
            int iterationsNum = Integer.parseInt(iterations);
            int lunchNum = Integer.parseInt(lunchTime);

            /* General stats */
            int totalBlock = studyNum + breakNum;
            int totalTime = totalBlock * iterationsNum;
            int totalStudyTime = studyNum * iterationsNum;
            int totalBreakTime = breakNum * iterationsNum + lunchNum;

            /* Setting labels. */
            timeLeftLabel.setText(getTimeLeftString(studyNum));
        }

    }

    private String getTimeLeftString(int studyNum)
    {
        int hours;
        int mins;

        if(studyNum >= 60)
        {
            hours = studyNum / 60;
            mins = studyNum % 60;
        }

        else
        {
            hours = 0;
            mins = studyNum;
        }

        String initialStudyBlockTime = hours + "h " + mins + "m";
        return initialStudyBlockTime;
    }

    private String getCurrentTimeString()
    {
        String time;
        String minString;
        Calendar now = Calendar.getInstance();

        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);

        if(hour > 12)
        {
            time = "pm";
        }
        else
        {
            time = "am";
        }

        if(minute < 10)
        {
            minString = "0" + minute;
        }
        else
        {
            minString = Integer.toString(minute);
        }

        return hour + ":" + minString + " " + time;
    }

    private void clockStuff() throws InterruptedException
    {
        /* Clock update stuff. */
        Calendar now = Calendar.getInstance();

        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);


        while(true)
        {
            int newTime = now.get(Calendar.MINUTE);

            if(newTime > minute || newTime == 0)
            {
                minute = newTime;
                hour = now.get(Calendar.HOUR_OF_DAY);
            }
            System.out.println("Current time: " + hour + ":" + minute);



            Thread.sleep(200);
        }
    }
}
