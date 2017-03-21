package fi.tamk.tiko5.hiddennature;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.ArrayList;

public class LevelSelect implements Screen{
    private HiddenNature hn;

    private Texture background;

    private Stage mainStage;

    private SpriteBatch batch;

    private Entity entity1;
    private Entity entity2;
    private Entity quit;
    private Entity start;
    private Entity levelEntity;
    private Level level;
    private int currentLevel;

    private ArrayList<Level> levels;

    public LevelSelect(HiddenNature hiddenNature) {
        currentLevel = 0;
        hn = hiddenNature;
        background = new Texture(Gdx.files.internal("background.jpeg"));
        batch = hn.getBatch();
        levels = new ArrayList<Level>();
        loadLevels();


        entity1 = new Entity("previous.png", "previouspressed.png", 20f, 210, 1, true);
        entity2 = new Entity("next.png", "nextpressed.png", 690f, 210, 2, true);

        quit = new Entity("return.png", "returnpressed.png", 690f, 390f, 3, true);
        start = new Entity("accept.png", "acceptpressed.png", 690f, 20f, 4, true);

        level = levels.get(0);

        mainStage = new Stage(new FitViewport(hn.getWORLD_WIDTH(), hn.getWORLD_HEIGHT()), batch);

        mainStage.addActor(entity1);
        mainStage.addActor(entity2);
        mainStage.addActor(quit);
        mainStage.addActor(start);
        mainStage.addActor(level);

        Gdx.input.setInputProcessor(mainStage);
}

    public void loadLevels(){
        levels.add(new Level(1, "mutsis", "kuva4.jpg", 0, hn.getWORLD_WIDTH(), hn.getWORLD_HEIGHT()));
        levels.add(new Level(2, "ebin", "kuva5.jpg", 0, hn.getWORLD_WIDTH(), hn.getWORLD_HEIGHT()));
        levels.add(new Level(3, "munasuuhun", "kuva6.jpg", 0, hn.getWORLD_WIDTH(), hn.getWORLD_HEIGHT()));
    }

    public void setLevel(int l){

        int tmp = currentLevel + l;
        if (tmp >= 0 && tmp <= levels.size() - 1){
            currentLevel = currentLevel + l;
            level.remove();
            level = levels.get(currentLevel);
            mainStage.addActor(level);
            Gdx.app.log("ripuli", ""+currentLevel);
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
