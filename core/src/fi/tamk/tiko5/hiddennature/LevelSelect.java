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
    private Entity entity1, entity2, quit, start, levelButton, tmp;
    private final byte LEVELCOUNT = 4;
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

    public LevelSelect(HiddenNature hiddenNature) {
        globalPrefs = Gdx.app.getPreferences("settings");
        levelText = 1;
        currentLevel = 0;
        hn = hiddenNature;
        background = new Texture(Gdx.files.internal("background.jpg"));
        batch = hn.getBatch();

        entity1 = new Entity("NuoliVasen.png", "NuoliVasenPushedButton.png", 20f, 210, 1, true, 0.25f);
        entity2 = new Entity("NuoliOikea.png", "NuoliOikeaPushedButton.png", 690f, 210, 2, true, 0.25f);
        tmp = new Entity("roundBox.jpg", "roundBox.jpg", 150, 80, 4, false, 1f);
        tmp.setSize(hn.getWORLD_WIDTH() - 300, hn.getWORLD_HEIGHT() - 160);

        levels = new Array<Entity>();

        levels.add(new Entity("l1/Taso1.jpg", "l1/Taso1.jpg", 160, 90, 4, true, 1f));
        levels.add(new Entity("l2/Taso2.jpg", "l2/Taso2.jpg", 160, 90, 4, true, 1f));
        levels.add(new Entity("l3/Taso3.jpg", "l3/Taso3.jpg", 160, 90, 4, true, 1f));
        levels.add(new Entity("l4/Taso4.png", "l4/Taso4.png", 160, 90, 4, true, 1f));

        for (Entity e : levels) {
            e.setSize(hn.getWORLD_WIDTH() - 320, hn.getWORLD_HEIGHT() - 180);
        }

        quit = new Entity("X.png", "xPushedButton.png", 690f, 390f, 3, true, 0.25f);
        start = new Entity("V.png", "vPushedButton.png", 690f, 20f, 4, true, 0.25f);
        levelButton = levels.get(currentLevel);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Calibri.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        param.size = 32;
        param.color = Color.GOLDENROD;
        param.borderWidth = 2;
        font = generator.generateFont(param);

        selectScreen();
}

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

    mainStage.addActor(entity1);
    mainStage.addActor(entity2);
    mainStage.addActor(quit);
    mainStage.addActor(start);
    mainStage.addActor(tmp);
    mainStage.addActor(levelButton);

    Gdx.input.setInputProcessor(mainStage);
    hn.setScreen(this);
}

    public void loadLevel(int id){

        switch (id){

            case 1:
                level = new Level(1, "Kesäinen metsä", "l1/Taso1.jpg", hn.getWORLD_WIDTH(), hn.getWORLD_HEIGHT());
                break;

            case 2:
                level = new Level(2, "Talvinen metsä", "l2/Taso2.jpg", hn.getWORLD_WIDTH(), hn.getWORLD_HEIGHT());
                break;

            case 3:
                level = new Level(3, "Syksy", "l3/Taso3.jpg", hn.getWORLD_WIDTH(), hn.getWORLD_HEIGHT());
                break;

            case 4:
                level = new Level(4, "Ranta", "l4/Taso4.png", hn.getWORLD_WIDTH(), hn.getWORLD_HEIGHT());
                break;

        }

    }

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

        if (currentLevel >= 0 && currentLevel < LEVELCOUNT){
            levelButton.remove();
            levelButton = levels.get(currentLevel);
            mainStage.addActor(levelButton);
        }
        levelText = currentLevel+1;
        levelName = levelNames.get(currentLevel);
        Gdx.app.log("currentlevel", ""+ currentLevel);
    }

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
                hn.pauseMenu = new PauseMenu(hn, level);
                hn.gameScreen = new GameScreen(hn, level, false);
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
        font.draw(batch, select +" "+ levelText + "/"+ LEVELCOUNT, 250, 450);
        font.draw(batch, levelName, 250, 60);

        mainStage.getBatch().end();
        mainStage.act(Gdx.graphics.getDeltaTime());
        mainStage.draw();

        getEntityID(entity1);
        getEntityID(entity2);
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

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }
}
