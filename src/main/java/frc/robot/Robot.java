package frc.robot;

import Subsytems.Pneumatics;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.ControllerConstants;

public class Robot extends TimedRobot {
    private Pneumatics m_Pneumatics = new Pneumatics();

    @Override
    public void robotInit() {
        
    }

    @Override
    public void teleopInit() {
        this.configureBindings();
    }

    @Override
    public void autonomousPeriodic() {
    }

    @Override
    public void robotPeriodic() {
        // Runs the Scheduler. This is responsible for polling buttons, adding
        // newly-scheduled
        // commands, running already-scheduled commands, removing finished or
        // interrupted commands,
        // and running subsystem periodic() methods. This must be called from the
        // robot's periodic
        // block in order for anything in the Command-based framework to work.
        CommandScheduler.getInstance().run();
    }

    @Override
    public void disabledPeriodic() {
    }

    private void configureBindings() {
        System.out.println("CONFIGURED");
        ControllerConstants.manipulatorController.y().and(m_Pneumatics::coneClosed).onTrue(m_Pneumatics.openConeCommand());
        ControllerConstants.manipulatorController.y().and(new Trigger(m_Pneumatics::coneClosed).negate()).onTrue(m_Pneumatics.closeConeCommand());

        ControllerConstants.manipulatorController.x().and(m_Pneumatics::cubeClosed).onTrue(m_Pneumatics.openCubeCommand());
        ControllerConstants.manipulatorController.x().and(new Trigger(m_Pneumatics::cubeClosed).negate()).onTrue(m_Pneumatics.closeCubeCommand());

        ControllerConstants.manipulatorController.rightTrigger(0.02).and(new Trigger(m_Pneumatics::liftOut).negate()).onTrue(m_Pneumatics.openLiftCommand());
        ControllerConstants.manipulatorController.rightTrigger(0.02).negate().and(m_Pneumatics::liftOut).onTrue(m_Pneumatics.closeLiftCommand());
    }
}
