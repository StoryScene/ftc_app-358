package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by KPSS on 26.09.17.
 */

@Disabled
public class ExpHub_Test extends OpMode{

    DcMotor right;

    public void init() {

        right = hardwareMap.dcMotor.get ("right");

    }
    public void loop ()
    {
        float right_power = -gamepad1.right_stick_y;

        right_power = Range.clip(right_power, -1, 1);

        right.setPower(right_power);
    }
}