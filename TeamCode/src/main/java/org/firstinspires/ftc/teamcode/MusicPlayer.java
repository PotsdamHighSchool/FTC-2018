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

    public MusicPlayer(HardwareMap hardwareMap, Gamepad gamepad1, Telemetry telemetry) {
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;
        this.gamepad1 = gamepad1;
        selectLogic = true;
        musicSelect = 0;
        songs.add("Cat");
        songs.add("He-Man");
        songs.add("The Best");
        songs.add("Africa by Toto");
        songs.add("AAAAAAAAAAAAAAAAAA");
        Rsongs.add(R.raw.cat);
        Rsongs.add(R.raw.heman);
        Rsongs.add(R.raw.thebest);
        Rsongs.add(R.raw.toto);
        Rsongs.add(R.raw.ahh);
    }

    public void run() {

        telemetry.addData("Selected", songs.get(musicSelect));
        telemetry.addData("MusicSelect", musicSelect);


        if (gamepad1.a) {
            if (player == null) {


                player = MediaPlayer.create(hardwareMap.appContext, Rsongs.get(musicSelect));
                player.start();

            }
        }
        if (gamepad1.b) {
            if (!(player == null)) {
                player.stop();
                player.reset();
                player = null;
            }
        }
        if (gamepad1.right_bumper && selectLogic) {
            musicSelect += 1;
            selectLogic = false;
            telemetry.update();
            if (musicSelect > (songs.size() - 1)) {
                musicSelect -= 1;
            }
        }
        if (gamepad1.left_bumper && selectLogic) {
            musicSelect -= 1;
            selectLogic = false;
            telemetry.update();
            if (musicSelect < 0) {
                musicSelect += 1;
            }
        }
        if (!gamepad1.left_bumper && !gamepad1.right_bumper) {
                selectLogic = true;
        }
    }
}

