package command;

public class MusicCommand implements Command{
    private Music music;

    public MusicCommand(Music music) {
        this.music = music;
    }

    @Override
    public void execute() {
        music.changeMusic();
    }
}
