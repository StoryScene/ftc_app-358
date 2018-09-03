package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by kevinwang on 8/24/18.
 */

public class Whatever_Drive_Code {

    static void Drive(DcMotor frontLeft, DcMotor backLeft, DcMotor frontRight, DcMotor backRight, int mode, double direction){

        // 0 mode is to stop
        // 1 mode is driving front and back, direction 1 is forward, direction -1 is backwards
        // 2 mode is strafing left and right, direction 1 is right, direction -1 is left
        // 3 mode is rotating, direction 1 is clockwise, direction -1 is counter-clockwise *not used

        double drive = 0;
        double strafe = 0;
        double rotate = 0;

        if (mode == 0){
            drive = 0;
            strafe = 0;
            rotate = 0;
        }

        else if (mode == 1){
            drive = direction;
            strafe = 0;
            rotate = 0;
        }

        else if (mode == 2){
            drive = 0;
            strafe = direction;
            rotate = 0;
        }

        else if (mode == 3){
            drive = 0;
            strafe = 0;
            rotate = direction;
        }

        else {
            frontLeft.setPower(0);
            backLeft.setPower(0);
            frontRight.setPower(0);
            backRight.setPower(0);
        }

        double frontLeftPower = drive - strafe - rotate;
        double backLeftPower = drive + strafe - rotate;
        double frontRightPower = drive + strafe + rotate;
        double backRightPower = drive - strafe + rotate;

        frontLeft.setPower(frontLeftPower);
        backLeft.setPower(backLeftPower);
        frontRight.setPower(frontRightPower);
        backRight.setPower(backRightPower);
    }

}
