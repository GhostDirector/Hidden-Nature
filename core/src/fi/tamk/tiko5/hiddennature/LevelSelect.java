package fi.tamk.tiko5.hiddennature;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * The screen Level select.
 */
public class LevelSelect implements Screen{
    private HiddenNature hn;
    private Texture background;
    private Stage mainStage;
    private SpriteBatch batch;
    private Level level;
    private int currentLevel;
    private String select, levelName;
    private BitmapFont font;
    private Preferences globalPrefs;
    private Entity leftArrow, rightArrow, quit, start, levelButton, tmp;
    /**
     * The Levelcount.
     */
    public final byte LEVELCOUNT = 5;
    private Array<Entity> levels;
    private Array<String> levelNames;
    private int levelText;

    @Override
    public void dispose() {
        font.dispose();
        batch.dispose();
        mainStage.dispose();
        background.dispose();
        hn.dispose();
    }

    /**
     * Instantiates a new Level select.
     *
     * @param hiddenNature main. Contains asset manager.
     */
    public LevelSelect(HiddenNature hiddenNature) {
        globalPrefs = Gdx.app.getPreferences("settings");
        levelText = 1;
        currentLevel = 0;
        hn = hiddenNature;
        background = hn.getAm().get("menu/background.jpg", Texture.class);
        batch = hn.getBatch();

        leftArrow = new Entity(hn.getAm().get("menu/NuoliVasen.png", Texture.class), hn.getAm().get("menu/NuoliVasenPushedButton.png", Texture.class), 20f, 210, 1, true, 0.25f);
        rightArrow = new Entity(hn.getAm().get("menu/NuoliOikea.png", Texture.class), hn.getAm().get("menu/NuoliOikeaPushedButton.png", Texture.class), 690f, 210, 2, true, 0.25f);
        tmp = new Entity(hn.getAm().get("menu/roundBox.jpg", Texture.class), hn.getAm().get("menu/roundBox.jpg", Texture.class), 150, 80, 4, false, 1f);
        tmp.setSize(hn.getWORLD_WIDTH() - 300, hn.getWORLD_HEIGHT() - 160);

        levels = new Array<Entity>();

        levels.add(new Entity(hn.getAm().get("l1/Taso1.jpg", Texture.class), hn.getAm().get("l1/Taso1.jpg", Texture.class), 160, 90, 4, true, 1f));
        levels.add(new Entity(hn.getAm().get("l2/Taso2.jpg", Texture.class), hn.getAm().get("l2/Taso2.jpg", Texture.class), 160, 90, 4, true, 1f));
        levels.add(new Entity(hn.getAm().get("l3/Taso3.jpg", Texture.class), hn.getAm().get("l3/Taso3.jpg", Texture.class), 160, 90, 4, true, 1f));
        levels.add(new Entity(hn.getAm().get("l4/Taso4.png", Texture.class), hn.getAm().get("l4/Taso4.png", Texture.class), 160, 90, 4, true, 1f));
        levels.add(new Entity(hn.getAm().get("l5/Taso5.jpg", Texture.class), hn.getAm().get("l5/Taso5.jpg", Texture.class), 160, 90, 4, true, 1f));

        for (Entity e : levels) {
            e.setSize(hn.getWORLD_WIDTH() - 320, hn.getWORLD_HEIGHT() - 180);
        }

        quit = new Entity(hn.getAm().get("menu/X.png", Texture.class), hn.getAm().get("menu/xPushedButton.png", Texture.class), 690f, 390f, 3, true, 0.25f);
        start = new Entity(hn.getAm().get("menu/V.png", Texture.class), hn.getAm().get("menu/vPushedButton.png", Texture.class), 690f, 20f, 4, true, 0.25f);
        levelButton = levels.get(currentLevel);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Calibri1.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        param.size = 30;
        param.color = Color.GOLDENROD;
        param.borderWidth = 2;
        font = generator.generateFont(param);

        selectScreen();
}

    /**
     * Select this screen. Reset stage. Set actors and listeners.
     */
    public void selectScreen() {
    if (mainStage != null) {
        mainStage.dispose();
    }
    
    levelNames = new Array<String>();
    levelNames.add(hn.getLocalization().get("l1"));
    levelNames.add(hn.getLocalization().get("l2"));
    levelNames.add(hn.getLocalization().get("l3"));
    levelNames.add(hn.getLocalization().get("l4"));
    levelNames.add(hn.getLocalization().get("l5"));
    levelName = levelNames.get(currentLevel);

    mainStage = new Stage(new FitViewport(hn.getWORLD_WIDTH(), hn.getWORLD_HEIGHT()), batch);

    select = hn.getLocalization().get("selLevelText");

    mainStage.addActor(leftArrow);
    mainStage.addActor(rightArrow);
    mainStage.addActor(quit);
    mainStage.addActor(start);
    mainStage.addActor(tmp);
    mainStage.addActor(levelButton);
    
    hideMenuArrows(currentLevel);

    Gdx.input.setInputProcessor(mainStage);
    hn.setScreen(this);
}

    /**
     * Loads level by level id.
     *
     * @param id the currently selected level.
     */
    public void loadLevel(int id){

        switch (id){

            case 1:
                level = new Level(1, hn.getAm().get("l1/Taso1.jpg", Texture.class), hn.getWORLD_WIDTH(), hn.getWORLD_HEIGHT(), hn);
                break;

            case 2:
                level = new Level(2, hn.getAm().get("l2/Taso2.jpg", Texture.class), hn.getWORLD_WIDTH(), hn.getWORLD_HEIGHT(), hn);
                break;

            case 3:
                level = new Level(3, hn.getAm().get("l3/Taso3.jpg", Texture.class), hn.getWORLD_WIDTH(), hn.getWORLD_HEIGHT(), hn);
                break;

            case 4:
                level = new Level(4,hn.getAm().get("l4/Taso4.png", Texture.class), hn.getWORLD_WIDTH(), hn.getWORLD_HEIGHT(), hn);
                break;

            case 5:
                level = new Level(5,hn.getAm().get("l5/Taso5.jpg", Texture.class), hn.getWORLD_WIDTH(), hn.getWORLD_HEIGHT(), hn);
                break;

        }

    }

    /**
     * Set thumbnail of selected level.
     *
     * @param l +1 or -1 depending on if right or left arrow was touched.
     */
    public void setLevel(int l){
        int tmp = currentLevel;

        if ((tmp + l) < 0){
            tmp = 0;
        } else if (tmp + l > LEVELCOUNT -1) {
            tmp = LEVELCOUNT -1;
        } else {
            tmp = tmp + l;
        }
        
        currentLevel = tmp;
        hideMenuArrows(currentLevel);

        if (currentLevel >= 0 && currentLevel < LEVELCOUNT){
            levelButton.remove();
            levelButton = levels.get(currentLevel);
            mainStage.addActor(levelButton);
        }
        levelText = currentLevel+1;
        levelName = levelNames.get(currentLevel);
        Gdx.app.log("currentlevel", ""+ currentLevel);
    }
    
    /**
     * Hide menu arrows when they do nothing. Example cant go left when at level 1 so don't show left arrow.
     *
     * @param i the current selected level.
     */
    public void hideMenuArrows(int i) {
        if (i == 0) {
            leftArrow.setVisible(false);
        } else {
            leftArrow.setVisible(true);
        }
    
        if (i == LEVELCOUNT -1) {
            rightArrow.setVisible(false);
        } else {
            rightArrow.setVisible(true);
        }
    }

    /**
     * Listens entities by id for actions
     *
     * @param entity the entity that was clicked.
     */
    public void getEntityID(Entity entity){
        int caseID = entity.getAction();

        switch (caseID){

            case 0: //Gdx.app.log("LevelSelect", "no actions");
                entity.resetAction();
                break;

            case 1: Gdx.app.log("LevelSelect", "previous level");
                setLevel(-1);
                entity.resetAction();
                break;

            case 2: Gdx.app.log("LevelSelect", "next level");
                setLevel(1);
                entity.resetAction();
                break;

            case 3: Gdx.app.log("LevelSelect", "Quit");
                hn.mainMenu.selectScreen();
                entity.resetAction();
                break;

            case 4: Gdx.app.log("LevelSelect", "Start");
                globalPrefs.putBoolean("Reset", false);
                loadLevel(currentLevel+1);
                hn.music.pause();

                if (globalPrefs.getInteger("sound", 1) == 1){
                    hn.gameMusic.play();
                }

                hn.pauseMenu = new PauseMenu(hn, level);
                hn.gameScreen = new GameScreen(hn, level);
                entity.resetAction();
                break;
        }
    }
    
    /**
     * Moves you to the next level after completing a level. Or moves you to credit screen after finishing game.
     *
     * @param levelID the level that was just completed.
     */
    public void toNextLevel(int levelID) {
        if (levelID < LEVELCOUNT) {
            setLevel(1);
            loadLevel(currentLevel + 1);
            hn.pauseMenu = new PauseMenu(hn, level);
            hn.gameScreen = new GameScreen(hn, level);
        } else {
            hn.gameMusic.pause();
            if (globalPrefs.getInteger("sound", 1) == 1){
                hn.music.play();
            }
            hn.levelSelect.selectScreen();
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mainStage.getBatch().begin();
        mainStage.getBatch().draw(background, 0, 0, hn.getWORLD_WIDTH(),  hn.getWORLD_HEIGHT());
        font.draw(batch, select +" "+ levelText + "/"+ LEVELCOUNT, 250, 450);
        font.draw(batch, levelName, 250, 60);

        mainStage.getBatch().end();
        mainStage.act(Gdx.graphics.getDeltaTime());
        mainStage.draw();

        getEntityID(leftArrow);
        getEntityID(rightArrow);
        getEntityID(quit);
        getEntityID(start);
        getEntityID(levelButton);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {

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
    public void hide() {

    }
}
