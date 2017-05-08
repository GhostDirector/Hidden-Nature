package fi.tamk.tiko5.hiddennature;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.Array;

/**
 * The Level. Contains information for levels.
 */
public class Level extends Actor{
    private int levelID;
    private Texture levelDiorama;
    private Array<Entity>originals = new Array<Entity>();
    private Array<Entity>silhouettes = new Array<Entity>();
    private Array<Entity>entities = new Array<Entity>();
    private Vector3 camPos;
    private float zoom;
    private ObjectManager objectManager;
    private HiddenNature hn;

    /**
     * Instantiates a new Level.
     *
     * @param id           the id
     * @param diorama      the diorama
     * @param width        the width
     * @param height       the height
     * @param hiddenNature the hidden nature
     */
    public Level(int id, Texture diorama, float width, float height, HiddenNature hiddenNature){
        hn = hiddenNature;
        levelID = id;
        levelDiorama = diorama;
        setBounds(160, 90, width - 320, height - 180);
        objectManager = new ObjectManager(this, hn);

        entities = objectManager.getEntities();
        originals = objectManager.getOriginals();
        silhouettes = objectManager.getSilhouettes();

        addListener(new InputListener() {

            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {

                return true;
            }
        });
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

    /**
     * Gets entities.
     *
     * @return the entities
     */
    public Array<Entity> getEntities() {
        return entities;
    }

    /**
     * Sets entities.
     *
     * @param foundEntities the found entities
     */
    public void setEntities(Array<Entity> foundEntities) {
        this.entities = foundEntities;
    }

    /**
     * Gets silhouettes.
     *
     * @return the silhouettes
     */
    public Array<Entity> getSilhouettes() {
        return silhouettes;
    }

    /**
     * Sets silhouettes.
     *
     * @param pauseSilhouettes the pause silhouettes
     */
    public void setSilhouettes(Array<Entity> pauseSilhouettes) {
        this.silhouettes = pauseSilhouettes;
    }

    /**
     * Gets originals.
     *
     * @return the originals
     */
    public Array<Entity> getOriginals() {
        return originals;
    }

    /**
     * Sets originals.
     *
     * @param originals the originals
     */
    public void setOriginals(Array<Entity> originals) {
        this.originals = originals;
    }

    /**
     * Get level diorama texture.
     *
     * @return the texture
     */
    public Texture getLevelDiorama(){
        return levelDiorama;
    }

    /**
     * Get level id int.
     *
     * @return the int
     */
    public int getLevelID(){
        return levelID;
    }

}
