package command;

public class BackGroundCommand implements Command{
    private BackGround backGround;

    public BackGroundCommand(BackGround backGround) {
        this.backGround = backGround;
    }

    @Override
    public void execute() {
        backGround.changeBackground();
    }
}
