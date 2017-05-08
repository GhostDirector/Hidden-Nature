package fi.tamk.tiko5.hiddennature;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * The Pause menu screen.
 */
public class PauseMenu implements Screen {

    private HiddenNature hn;
    private Stage pauseStage;
    private SpriteBatch batch;
    private Level level;
    private Texture background;
    private Preferences globalPrefs;
    private Entity menuButton, returnButton, helpButton, tutorialBox;
    private Array<Entity>entities = new Array<Entity>();
    private Array<Entity>originals = new Array<Entity>();
    private Array<Entity>silhouettes = new Array<Entity>();

    @Override
    public void dispose() {
        background.dispose();
        batch.dispose();
        pauseStage.dispose();
        hn.dispose();
    }

    /**
     * Instantiates a new Pause menu.
     *
     * @param hiddenNature main. Contains asset manager.
     * @param l            the current level.
     */
    public PauseMenu(HiddenNature hiddenNature, Level l){

        hn = hiddenNature;
        level = l;
        batch = hn.getBatch();
        entities = level.getEntities();
        originals = level.getOriginals();
        silhouettes = level.getSilhouettes();
        globalPrefs = Gdx.app.getPreferences("settings");

        background = hn.getAm().get("menu/PauseMenuFancy.jpg", Texture.class);
        menuButton = new Entity(hn.getAm().get("menu/X.png", Texture.class), hn.getAm().get("menu/xPushedButton.png", Texture.class), 690f, 390f, 1, true, 0.25f);
        helpButton = new Entity(hn.getAm().get("menu/HelpButton.png", Texture.class), hn.getAm().get("menu/HelpPushedButton.png", Texture.class), 690f, 290f, 3, true, 0.25f);
        
        selectScreen();
    }

    /**
     * Select this screen. Reset stage. Set actors and listeners.
     */
    public void selectScreen() {
        if (pauseStage != null) {
            pauseStage.dispose();
        }

        pauseStage = new Stage(new FitViewport(hn.getWORLD_WIDTH(), hn.getWORLD_HEIGHT()), batch);
        returnButton = new Entity(hn.getAm().get(hn.getLocalization().get("returnToMenu"), Texture.class), hn.getAm().get(hn.getLocalization().get("returnToMenuPressed"), Texture.class), 280f, 10f, 2, true, 0.50f);
        tutorialBox = new Entity(hn.getAm().get(hn.getLocalization().get("pauseTutBox"), Texture.class), hn.getAm().get(hn.getLocalization().get("pauseTutBox"), Texture.class), 0f, 0f, 4, true, 1f);
        tutorialBox.setSize(hn.getWORLD_WIDTH(), hn.getWORLD_HEIGHT());


        for (int i = 0; i < originals.size; i++){
            if (originals.get(i).isFound()) {
                originals.get(i).setPosition(silhouettes.get(i).getX(), silhouettes.get(i).getY());
            }
        }

        for (Entity e : silhouettes) {
            if (!e.isFound()) {
                pauseStage.addActor(e);
            }
        }

        for (Entity e : originals) {
            if (e.isFound()) {
                pauseStage.addActor(e);
            }
        }

        pauseStage.addActor(menuButton);
        pauseStage.addActor(returnButton);
        pauseStage.addActor(helpButton);

        Gdx.input.setInputProcessor(pauseStage);
        hn.setScreen(this);
    }

    /**
     * Listens entities by id for actions
     *
     * @param entity the entity that was clicked.
     */
    public void getEntityID(Entity entity){
        switch (entity.getAction()){

            case 0: //Gdx.app.log("pauseMenu", "no actions");
                entity.resetAction();
                break;

            case 1:Gdx.app.log("pauseMenu", "menubutton");
                level.setEntities(entities);
                level.setOriginals(originals);
                level.setSilhouettes(silhouettes);
                hn.gameScreen.selectScreen();
                entity.resetAction();
                break;

            case 2:Gdx.app.log("pauseMenu", "back");
                hn.gameMusic.pause();
                if (globalPrefs.getInteger("sound", 1) == 1){
                    hn.music.play();
                }
                hn.levelSelect.selectScreen();
                entity.resetAction();
                break;

            case 3:Gdx.app.log("HelpButton", "help");
                pauseStage.addActor(tutorialBox);
                entity.resetAction();
                break;

            case 4:Gdx.app.log("Tutorial", "tutButton");
                tutorialBox.remove();
                entity.resetAction();
                break;
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        pauseStage.getBatch().begin();
        pauseStage.getBatch().draw(background, 0, 0, hn.getWORLD_WIDTH(),  hn.getWORLD_HEIGHT());

        pauseStage.getBatch().end();

        pauseStage.act(Gdx.graphics.getDeltaTime());
        pauseStage.draw();

        getEntityID(menuButton);
        getEntityID(returnButton);
        getEntityID(helpButton);
        getEntityID(tutorialBox);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        hn.gameMusic.pause();
    }

    @Override
    public void resume() {
        if (globalPrefs.getInteger("sound", 1) == 1){
            hn.gameMusic.play();
        }
    }

    @Override
    public void hide() {

    }
}
