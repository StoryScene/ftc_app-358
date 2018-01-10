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

// test

@Disabled
public class KPSS extends OpMode{

    DcMotor right;
    DcMotor left;

    public void init() {

        right = hardwareMap.dcMotor.get ("retract");
        left = hardwareMap.dcMotor.get ("release");
        left.setDirection(DcMotorSimple.Direction.REVERSE);

    }
    public void loop ()
    {

        float left_power = gamepad1.left_stick_y;
        float right_power = gamepad1.right_stick_y;

        left_power = Range.clip(left_power, -1, 1);
        right_power = Range.clip(right_power, -1, 1);

        left.setPower(left_power);
        right.setPower(right_power);

    }
}