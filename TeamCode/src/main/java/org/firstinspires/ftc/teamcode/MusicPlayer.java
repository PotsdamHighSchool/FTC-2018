package org.firstinspires.ftc.teamcode;

/**
 * Created by mbusch356 on 2/16/2018.
 */

    import android.media.MediaPlayer;
    import com.qualcomm.robotcore.hardware.Gamepad;
    import com.qualcomm.robotcore.hardware.HardwareMap;
    import org.firstinspires.ftc.robotcore.external.Telemetry;
    import java.util.ArrayList;
    import java.util.LinkedList;

    public class MusicPlayer {
    private MediaPlayer player;
    private boolean selectLogic;
    private int musicSelect;
    private java.util.List songs = new LinkedList();
    private ArrayList<Integer> Rsongs = new ArrayList<>();
    private Gamepad gamepad1;
    private Telemetry telemetry;
    private HardwareMap hardwareMap;
    private boolean logic;
    private boolean start;

    public MusicPlayer(HardwareMap hardwareMap, Gamepad gamepad1, Telemetry telemetry) {
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;
        this.gamepad1 = gamepad1;
        selectLogic = true;
        musicSelect = 4;
        songs.add("Africa by Toto");
        songs.add("Cat");
        songs.add("He-Man");
        songs.add("AAAAAAAAAAAAAAAAAA");
        songs.add("Silence");
        Rsongs.add(R.raw.toto);
        Rsongs.add(R.raw.cat);
        Rsongs.add(R.raw.heman);
        Rsongs.add(R.raw.ahh);
        logic = true;
        start = true;
    }

    public void run() {

        telemetry.addData("Selected", songs.get(musicSelect));
        telemetry.addData("MusicSelect", musicSelect);


        if (gamepad1.start && logic && start) {
            if (player == null && musicSelect != 4) {
                player = MediaPlayer.create(hardwareMap.appContext, Rsongs.get(musicSelect));
                player.start();
                logic = false;
                start = false;
            }
        }
        else if (gamepad1.start &&  logic && !start) {
            if (player != null) {
                player.stop();
                player.reset();
                player = null;
                start = true;
                logic = false;
            }
        }
        if (!gamepad1.start && !logic){
            logic = true;
        }
        if (gamepad1.right_trigger > 0.1 && selectLogic) {
            musicSelect += 1;
            selectLogic = false;
            telemetry.update();
            if (musicSelect > (songs.size() - 1)) {
                musicSelect -= 1;
            }
        }
        if (gamepad1.left_trigger > 0.1 && selectLogic) {
            musicSelect -= 1;
            selectLogic = false;
            telemetry.update();
            if (musicSelect < 0) {
                musicSelect += 1;
            }
        }
        if (gamepad1.left_trigger == 0.0 && gamepad1.right_trigger == 0.0) {
                selectLogic = true;
        }
    }
}

