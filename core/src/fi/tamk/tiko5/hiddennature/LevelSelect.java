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
import com.badlogic.gdx.utils.viewport.FitViewport;
import java.util.ArrayList;

public class LevelSelect implements Screen{
    private HiddenNature hn;
    private Texture background;
    private Stage mainStage;
    private SpriteBatch batch;
    private Level level;
    private int currentLevel;
    private String select, found;
    private BitmapFont font;
    private ArrayList<Level>levels;
    private Preferences globalPrefs;
    private boolean reset;
    private Entity entity1, entity2, quit, start, levelEntity;

    public LevelSelect(HiddenNature hiddenNature) {
        globalPrefs = Gdx.app.getPreferences("settings");
        reset = globalPrefs.getBoolean("Reset", false);
        Gdx.app.log("level select","");
        currentLevel = 0;
        hn = hiddenNature;
        background = new Texture(Gdx.files.internal("menu2.png"));
        batch = hn.getBatch();
        levels = new ArrayList<Level>();
        loadLevels();

        select = hn.getLocalization().get("selLevelText");
        found = hn.getLocalization().get("foundText");

        entity1 = new Entity("NuoliVasen.png", "NuoliVasen.png", 20f, 210, 1, true, 0.25f);
        entity2 = new Entity("NuoliOikea.png", "NuoliOikea.png", 690f, 210, 2, true, 0.25f);

        quit = new Entity("X.png", "X.png", 690f, 390f, 3, true, 0.25f);
        start = new Entity("V.png", "V.png", 690f, 20f, 4, true, 0.25f);

        level = levels.get(0);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("comic.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        param.size = 32;
        param.color = Color.DARK_GRAY;
        param.borderWidth = 2;
        font = generator.generateFont(param);

        mainStage = new Stage(new FitViewport(hn.getWORLD_WIDTH(), hn.getWORLD_HEIGHT()), batch);

        mainStage.addActor(entity1);
        mainStage.addActor(entity2);
        mainStage.addActor(quit);
        mainStage.addActor(start);
        mainStage.addActor(level);

        Gdx.input.setInputProcessor(mainStage);
}

    public void loadLevels(){
        levels.add(new Level(1, "demo", "Taso1.png", 0, hn.getWORLD_WIDTH(), hn.getWORLD_HEIGHT(), reset));
        levels.add(new Level(2, "talvinenMetsa", "talvinenMetsa.png", 0, hn.getWORLD_WIDTH(), hn.getWORLD_HEIGHT(), reset));
        levels.add(new Level(3, "test2", "testi2.png", 0, hn.getWORLD_WIDTH(), hn.getWORLD_HEIGHT(), reset));
        
        globalPrefs.putBoolean("Reset", false);
        globalPrefs.flush();
    }

    public void setLevel(int l){

        int tmp = currentLevel + l;
        if (tmp >= 0 && tmp <= levels.size() - 1){
            currentLevel = currentLevel + l;
            level.remove();
            level = levels.get(currentLevel);
            mainStage.addActor(level);
            Gdx.app.log("currentLevel", ""+currentLevel);
        }
    }

    public void getEntityID(Entity entity){
        switch (entity.getAction()){

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
                hn.setScreen(new MainMenu(hn));
                entity.resetAction();
                break;

            case 4: Gdx.app.log("LevelSelect", "Start");
                hn.setScreen(new GameScreen(hn, levels.get(currentLevel), false));
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
        font.draw(batch, select + levels.get(currentLevel).getLevelID()+ "/"+ levels.size(), 150, 450);
        font.draw(batch, found + 0 +"/"+ 0, 150, 60);
        
        mainStage.getBatch().end();
        mainStage.act(Gdx.graphics.getDeltaTime());
        mainStage.draw();

        getEntityID(entity1);
        getEntityID(entity2);
        getEntityID(quit);
        getEntityID(start);
    }

    @Override
    public void dispose() {

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
