package fi.tamk.tiko5.hiddennature;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * The Game screen, provides rendering and user interface for actual gameplay.
 */
public class GameScreen extends MyAdapter implements Screen{

    private HiddenNature hn;
    private Texture diorama;
    private Stage gameStage;
    private SpriteBatch batch;
    private Entity menuOpenButton, tutorialBox, completeBox;
    private InputMultiplexer imp;
    private GestureDetector gd;
    private boolean menuOpen;
    private Level level;
    private Array<Entity>originals = new Array<Entity>();
    private Array<Entity>entities = new Array<Entity>();
    private Array<Entity>silhouettes = new Array<Entity>();
    private PrefHandler prefs;
    private Preferences globalPrefs;
    private boolean showTutorial, completed;
    private int counter;


    @Override
    public void dispose() {
        batch.dispose();
        gameStage.dispose();
        diorama.dispose();
        hn.dispose();
    }

    /**
     * Instantiates a new Game screen.
     *
     * @param hiddenNature main. Contains asset manager.
     * @param l            Selected level
     */
    public GameScreen(HiddenNature hiddenNature, Level l) {

        level = l;
        diorama = level.getLevelDiorama();
        imp = new InputMultiplexer();
        hn = hiddenNature;
        menuOpen = false;
        entities = level.getEntities();
        originals = level.getOriginals();
        silhouettes = level.getSilhouettes();
        prefs = new PrefHandler(level, hn);
        gd = new GestureDetector(this);

        globalPrefs = Gdx.app.getPreferences("settings");

        if (globalPrefs.getBoolean("Reset"+level.getLevelID(), false)) {
            globalPrefs.putBoolean("Completed"+level.getLevelID(), false);
        }

        globalPrefs.putBoolean("Reset"+level.getLevelID(), false);
        completed = globalPrefs.getBoolean("Completed"+level.getLevelID(), false);

        showTutorial = globalPrefs.getBoolean("Tutorial", true);
        globalPrefs.putBoolean("Tutorial", false);
        globalPrefs.flush();

        menuOpenButton = new Entity(hn.getAm().get("menu/PauseMenu.png", Texture.class), hn.getAm().get("menu/PauseMenuPushedButton.png", Texture.class), 690f, 390f, 1, true, 0.25f);

        batch = hn.getBatch();
        
        selectScreen();
    }


    /**
     * Select this screen, reset stage, set actors and listeners.
     */
    public void selectScreen() {
        if (gameStage != null) {
            gameStage.dispose();
        }
        
        imp.clear();
        gameStage = new Stage(new FitViewport(hn.getWORLD_WIDTH(), hn.getWORLD_HEIGHT()), batch);

        for (Entity e : entities) {
            if (!e.isFound()) {
                gameStage.addActor(e);
            }
        }

        gameStage.addActor(menuOpenButton);
        tutorialBox = new Entity(hn.getAm().get(hn.getLocalization().get("tutBox"), Texture.class), 120f, 600f, 2, true, 0.48f);
        completeBox = new Entity(hn.getAm().get(hn.getLocalization().get("completeBox"), Texture.class), 0f, 600f, 3, true, 1f);
        completeBox.setSize(hn.getWORLD_WIDTH(), hn.getWORLD_HEIGHT());

        if (showTutorial) {
            gameStage.addActor(tutorialBox);
        }

        imp.addProcessor(gameStage);
        imp.addProcessor(gd);
        imp.addProcessor(this);

        Gdx.input.setInputProcessor(imp);

        hn.setScreen(this);
    }

    /**
     * Listens entities by id for actions.
     *
     * @param entity the entity that was clicked.
     */
    public void getEntityID(Entity entity){
        switch (entity.getAction()){

            case 0: //Gdx.app.log("GameScreen", "no actions");
                entity.resetAction();
                break;

            case 1: Gdx.app.log("GameScreen", "pausemenu");
                level.setOriginals(originals);
                level.setEntities(entities);
                level.setSilhouettes(silhouettes);
                prefs.save();
                hn.pauseMenu.selectScreen();
                entity.resetAction();
                break;

            case 2: showTutorial = false;
                entity.resetAction();
                selectScreen();
                break;

            case 3: globalPrefs.putBoolean("Completed"+level.getLevelID(), true);
                level.setOriginals(originals);
                level.setEntities(entities);
                level.setSilhouettes(silhouettes);
                prefs.save();
                entity.resetAction();
                hn.levelSelect.toNextLevel(level.getLevelID());
                break;
        }

        if (entity.getAction() < 0){
            entity.setFound(true);
            entity.resetAction();

        }
    }

    @Override
    public void render(float delta) {

        if (!menuOpen) {
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


            gameStage.getBatch().begin();
            gameStage.getBatch().draw(diorama, 0, 0, hn.getWORLD_WIDTH(), hn.getWORLD_HEIGHT());
            gameStage.getBatch().end();

            gameStage.act(Gdx.graphics.getDeltaTime());
            gameStage.draw();
            gameStage.getCamera().update();

            
            getEntityID(tutorialBox);
            
            getEntityID(menuOpenButton);
            getEntityID(completeBox);

            counter = 0;
            for (Entity e : entities) {

                getEntityID(e);

                if (e.isFound()) {
                    for (Entity f : originals) {
                        if (e.getButtonID() == f.getButtonID()) {
                            f.setFound(true);
                        }
                    }
                    for (Entity f : silhouettes) {
                        if (e.getButtonID() == f.getButtonID()) {
                            f.setFound(false);
                        }
                    }

                    counter++;
                    System.out.println(counter);
                }

                if (counter == entities.size && !completed) {
                    gameStage.addActor(completeBox);
                    completeBox.slide();
                }
            }
        }

        if (showTutorial) {
            tutorialBox.slide();
        }
    }

    @Override
    public void show() {

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
