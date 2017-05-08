package fi.tamk.tiko5.hiddennature;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Credits implements Screen {
    private HiddenNature hn;
    private Texture background;
    private Stage creditsStage;
    private SpriteBatch batch;
    private Entity entity1, entity2, entity3, entity4, entity5, entity6;
    private BitmapFont font;
    private boolean gameIsOn;
    private String creditsText, leadText, programmingText, graphicsText, collabText;

    @Override
    public void dispose() {
        font.dispose();
        batch.dispose();
        creditsStage.dispose();
        background.dispose();
        hn.dispose();
    }

    public Credits(HiddenNature hiddenNature){
        gameIsOn = true;
        hn = hiddenNature;
        background = hn.getAm().get("menu/background.jpg", Texture.class);
        batch = hn.getBatch();
    
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Calibri1.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        param.size = 20;
        param.color = Color.GOLDENROD;
        param.borderWidth = 2;
        font = generator.generateFont(param);
    
        entity1 = new Entity(hn.getAm().get("menu/X.png", Texture.class), hn.getAm().get("menu/xPushedButton.png", Texture.class), 690f, 390f, 1, true, 0.25f);
        entity6 = new Entity(hn.getAm().get("logos/Lumu.png", Texture.class), hn.getAm().get("logos/Lumu.png", Texture.class), 530f, 240f, 6, true, 0.19f);
        
        selectScreen();
    }
    
    public void selectScreen() {
        if (creditsStage != null) {
            creditsStage.dispose();
        }
        
        creditsStage = new Stage(new FitViewport(hn.getWORLD_WIDTH(), hn.getWORLD_HEIGHT()), batch);
    
        entity2 = new Entity(hn.getAm().get(hn.getLocalization().get("trashLogo"), Texture.class), hn.getAm().get(hn.getLocalization().get("trashLogo"), Texture.class), 370f, 240f, 2, true, 0.15f);
        entity3 = new Entity(hn.getAm().get(hn.getLocalization().get("tamkLogo"), Texture.class), hn.getAm().get(hn.getLocalization().get("tamkLogo"), Texture.class), 40f, 40f, 3, true, 0.07f);
        entity4 = new Entity(hn.getAm().get(hn.getLocalization().get("tikoLogo"), Texture.class), hn.getAm().get(hn.getLocalization().get("tikoLogo"), Texture.class), 260f, 40f, 4, true, 0.35f);
        entity5 = new Entity(hn.getAm().get(hn.getLocalization().get("vapriikkiLogo"), Texture.class), hn.getAm().get(hn.getLocalization().get("vapriikkiLogo"), Texture.class), 520f, 40f, 5, true, 0.35f);


        creditsStage.addActor(entity1);
        creditsStage.addActor(entity2);
        creditsStage.addActor(entity3);
        creditsStage.addActor(entity4);
        creditsStage.addActor(entity5);
        creditsStage.addActor(entity6);

        creditsText = hn.getLocalization().get("credits");
        leadText = hn.getLocalization().get("creLead");
        programmingText = hn.getLocalization().get("creProg");
        graphicsText = hn.getLocalization().get("creGraph");
        collabText =  hn.getLocalization().get("collabText");

        Gdx.input.setInputProcessor(creditsStage);

        hn.setScreen(this);
    }

    public void getEntityID(Entity entity){
        switch (entity.getAction()){

            case 0: //Gdx.app.log("entity", "no actions");
                entity.resetAction();
                break;

            case 1: hn.mainMenu.selectScreen();
                entity.resetAction();
                break;

            case 2:
                Gdx.net.openURI(hn.getLocalization().get("trashURL"));
                entity.resetAction();
                break;

            case 3:
                Gdx.net.openURI(hn.getLocalization().get("tamkURL"));
                entity.resetAction();
                break;

            case 4:
                Gdx.net.openURI("http://tiko.blogs.tamk.fi/");
                entity.resetAction();
                break;

            case 5:
                Gdx.net.openURI(hn.getLocalization().get("vapriikkiURL"));
                entity.resetAction();
                break;

            case 6:
                Gdx.net.openURI(hn.getLocalization().get("lumuURL"));
                entity.resetAction();
                break;
        }
    }

    @Override
    public void render(float delta) {
        if (gameIsOn){
            creditsStage.getBatch().begin();
            creditsStage.getBatch().draw(background, 0, 0, hn.getWORLD_WIDTH(), hn.getWORLD_HEIGHT());
            font.draw(batch, creditsText, 50, 450);
            font.draw(batch, leadText + " Kaisa Tikkanen", 50, 400);
            font.draw(batch, programmingText +" Joni Tuominen", 50, 370);
            font.draw(batch, programmingText + " Jyri Virtaranta", 50, 340);
            font.draw(batch, graphicsText + " Severi Törmä", 50, 310);

            font.draw(batch, collabText, 50, 220);

            creditsStage.getBatch().end();
            creditsStage.act(Gdx.graphics.getDeltaTime());
            creditsStage.draw();

            getEntityID(entity1);
            getEntityID(entity2);
            getEntityID(entity3);
            getEntityID(entity4);
            getEntityID(entity5);
            getEntityID(entity6);
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
        hn.music.pause();
        gameIsOn = false;
    }

    @Override
    public void resume() {
        gameIsOn = true;
        if (hn.isSound()){
            hn.music.play();
        }
    }

    @Override
    public void hide() {

    }
}
