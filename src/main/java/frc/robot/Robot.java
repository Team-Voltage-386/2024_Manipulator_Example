package frc.robot;

import Subsytems.Pneumatics;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.ControllerConstants;

public class Robot extends TimedRobot {
    private Pneumatics m_Pneumatics = new Pneumatics();

    @Override
    public void robotInit() {
        this.configureBindings();
    }

    @Override
    public void teleopInit() {

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
        Trigger yAndConeClosed = ControllerConstants.manipulatorController.y().and(m_Pneumatics::coneClosed);
        yAndConeClosed.onTrue(m_Pneumatics.openConeCommand());
        yAndConeClosed.onFalse(m_Pneumatics.closeConeCommand());

        Trigger xAndCubeClosed = ControllerConstants.manipulatorController.x().and(m_Pneumatics::cubeClosed);
        xAndCubeClosed.onTrue(m_Pneumatics.openCubeCommand());
        xAndCubeClosed.onFalse(m_Pneumatics.closeCubeCommand());

        ControllerConstants.manipulatorController.rightTrigger(0.02).and(new Trigger(m_Pneumatics::liftOut).negate()).onTrue(m_Pneumatics.openLiftCommand());
        ControllerConstants.manipulatorController.rightTrigger(0.02).negate().and(m_Pneumatics::liftOut).onTrue(m_Pneumatics.closeLiftCommand());
    }
}
