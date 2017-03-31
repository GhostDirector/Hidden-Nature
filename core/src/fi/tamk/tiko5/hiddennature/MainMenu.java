package fi.tamk.tiko5.hiddennature;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class MainMenu implements Screen {
    private HiddenNature hn;
    private Texture background;
    private Stage mainStage;
    private SpriteBatch batch;
    private Entity entity1, entity2, entity3, entity4;

    public MainMenu(HiddenNature hiddenNature){
        background = new Texture(Gdx.files.internal("menu2.png"));
        hn = hiddenNature;
        batch = hn.getBatch();
        
        entity1 = new Entity(hn.getLocalization().get("playButton"), "PLAY.png", 600f, 350f, 1, true, 0.65f);
        entity2 = new Entity(hn.getLocalization().get("creditsButton"), "CREDITS.png" ,600f, 200f, 2, true, 0.65f);
        entity3 = new Entity(hn.getLocalization().get("quitButton"), "QUIT.png", 600f, 50f, 3, true, 0.65f);
        entity4 = new Entity("PauseMenu.png", "PauseMenu.png", 30f, 25f, 4, true, 0.28f);

        mainStage = new Stage(new FitViewport(hn.getWORLD_WIDTH(), hn.getWORLD_HEIGHT()), batch);

        mainStage.addActor(entity1);
        mainStage.addActor(entity2);
        mainStage.addActor(entity3);
        mainStage.addActor(entity4);

        Gdx.input.setInputProcessor(mainStage);
    }

    public void getEntityID(Entity entity){
        switch (entity.getAction()){

            case 0: //Gdx.app.log("MainMenu", "no actions");
                break;

            case 1:Gdx.app.log("MainMenu", "Start");
                hn.setScreen(new LevelSelect(hn));
                entity.resetAction();
                break;

            case 2:Gdx.app.log("MainMenu", "credits screen");
                hn.setScreen(new Credits(hn));
                entity.resetAction();
                break;

            case 3:Gdx.app.log("MainMenu", "quit game");
                System.exit(0);
                entity.resetAction();
                break;

            case 4:Gdx.app.log("MainMenu", "settings");
                hn.setScreen(new Settings(hn));
                entity.resetAction();
                break;
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mainStage.getBatch().begin();
        mainStage.getBatch().draw(background, 0, 0, hn.getWORLD_WIDTH(),  hn.getWORLD_HEIGHT());
        mainStage.getBatch().end();

        mainStage.act(Gdx.graphics.getDeltaTime());

        mainStage.draw();

        getEntityID(entity1);
        getEntityID(entity2);
        getEntityID(entity3);
        getEntityID(entity4);
    }

    @Override
    public void dispose() {

    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide(){
    }

}