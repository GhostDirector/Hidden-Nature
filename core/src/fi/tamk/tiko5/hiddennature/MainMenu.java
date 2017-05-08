package fi.tamk.tiko5.hiddennature;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * The Main menu.
 */
public class MainMenu implements Screen {
    private HiddenNature hn;
    private Texture background;
    private Stage mainStage;
    private SpriteBatch batch;
    private Entity entity1, entity2, entity3, entity4, entity5;

    @Override
    public void dispose() {
        batch.dispose();
        mainStage.dispose();
        background.dispose();
        hn.dispose();
    }

    /**
     * Instantiates a new Main menu.
     *
     * @param hiddenNature main. Contains asset manager.
     */
    public MainMenu(HiddenNature hiddenNature){
        hn = hiddenNature;
        int mute = hn.globalPrefs.getInteger("sound", 1);
        switch (mute) {
            case 1: hn.music.play();
                break;

            case 2: hn.music.pause();
        }
        background = hn.getAm().get("menu/background.jpg", Texture.class);
        batch = hn.getBatch();
        
        entity4 = new Entity(hn.getAm().get("menu/SettingsButton.png", Texture.class), hn.getAm().get("menu/SettingsPushedButton.png", Texture.class), 30f, 25f, 4, true, 0.28f);
        entity5 = new Entity(hn.getAm().get("menu/title.png", Texture.class), hn.getAm().get("menu/title.png", Texture.class), 20f, 270f, 6, false, 0.40f);

        selectScreen();
    }

    /**
     * Select this screen. Reset stage. Set actors and listeners.
     */
    public void selectScreen() {
        if (mainStage != null) {
            mainStage.dispose();
        }
    
        entity1 = new Entity(hn.getAm().get(hn.getLocalization().get("playButton"), Texture.class), hn.getAm().get(hn.getLocalization().get("playpressedButton"), Texture.class), 600f, 350f, 1, true, 0.65f);
        entity2 = new Entity(hn.getAm().get(hn.getLocalization().get("creditsButton"), Texture.class), hn.getAm().get(hn.getLocalization().get("creditspressedButton"), Texture.class) ,600f, 200f, 2, true, 0.65f);
        entity3 = new Entity(hn.getAm().get(hn.getLocalization().get("quitButton"), Texture.class), hn.getAm().get(hn.getLocalization().get("quitpressedButton"), Texture.class), 600f, 50f, 3, true, 0.65f);
        
        mainStage = new Stage(new FitViewport(hn.getWORLD_WIDTH(), hn.getWORLD_HEIGHT()), batch);

        mainStage.addActor(entity1);
        mainStage.addActor(entity2);
        mainStage.addActor(entity3);
        mainStage.addActor(entity4);
        mainStage.addActor(entity5);

        Gdx.input.setInputProcessor(mainStage);
        hn.setScreen(this);
    }

    /**
     * Listens entities by id for actions
     *
     * @param entity the entity that was clicked.
     */
    public void getEntityID(Entity entity){
        switch (entity.getAction()){

            case 0: //Gdx.app.log("MainMenu", "no actions");
                break;

            case 1:Gdx.app.log("MainMenu", "Start");
                hn.levelSelect.selectScreen();
                entity.resetAction();
                break;

            case 2:Gdx.app.log("MainMenu", "credits screen");
                hn.credits.selectScreen();
                entity.resetAction();
                break;

            case 3:Gdx.app.log("MainMenu", "quit game");
                Gdx.app.exit();
                //System.exit(0);
                entity.resetAction();
                break;

            case 4:Gdx.app.log("MainMenu", "settings");
                hn.settings.selectScreen();
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
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        hn.music.pause();
    }

    @Override
    public void resume() {
        if (hn.globalPrefs.getInteger("sound", 1) == 1){
            hn.music.play();
        }
    }

    @Override
    public void hide(){
    }

}