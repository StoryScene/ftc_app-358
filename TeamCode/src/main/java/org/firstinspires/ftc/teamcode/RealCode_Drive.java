package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

public class RealCode_Drive {

    public static void Rotate (DcMotor m1, DcMotor m2, DcMotor m3, DcMotor m4, double power,int distance) {

        //Reset Encoders
        m1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        m2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        m3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        m4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Set to RUN_TO_POSITION mode
        m1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        m2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        m3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        m4.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //Set Target Position
        m1.setTargetPosition(distance);
        m2.setTargetPosition(distance);
        m3.setTargetPosition(distance);
        m4.setTargetPosition(distance);

        //Set Drive Power
        m1.setPower(power);
        m2.setPower(power);
        m3.setPower(power);
        m4.setPower(power);

        while (m1.isBusy() && m2.isBusy() && m3.isBusy() && m4.isBusy())
        {
            //Wait Until Target Position is Reached
        }

        //Stop and Change Mode back to Normal
        m1.setPower(0);
        m2.setPower(0);
        m3.setPower(0);
        m4.setPower(0);
    }

    public static void Turn (DcMotor left, DcMotor right, double power,int distance) {

        //positive distance to turn left; negative distance to turn right

        //Reset Encoders
        left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Set to RUN_TO_POSITION mode
        left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        right.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //Set Target Position
        left.setTargetPosition(-1*distance);
        right.setTargetPosition(distance);

        //Set Drive Power
        left.setPower(power);
        right.setPower(power);

        while (left.isBusy() && right.isBusy())
        {
            //Wait Until Target Position is Reached
        }

        //Stop and Change Mode back to Normal
        left.setPower(0);
        right.setPower(0);
    }

}