package fi.tamk.tiko5.hiddennature;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Level extends Actor{

    private int levelID;
    private String levelName;
    private Texture levelDiorama;
    private int entitiesFound;

    public Level(int id, String name, String diorama, int found, float width, float height){
        levelID = id;
        levelName = name;
        levelDiorama = new Texture(Gdx.files.internal(diorama));
        entitiesFound = found;
        setBounds(160, 90, width - 320, height - 180);
    }

    public Texture getLevelDiorama(){
        return levelDiorama;
    }

    public int getLevelID(){
        return levelID;
    }

    public String getLevelName(){
        return levelName;
    }

    public int getEntitiesFound(){
        return entitiesFound;
    }

    @Override
    public void draw(Batch batch, float alpha) {
        batch.draw(levelDiorama,
                this.getX(), this.getY(),
                this.getOriginX(),
                this.getOriginY(),
                this.getWidth(),
                this.getHeight(),
                this.getScaleX(),
                this.getScaleY(),
                this.getRotation(),0,0,
                levelDiorama.getWidth(), levelDiorama.getHeight(), false, false);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

    }
    
}
