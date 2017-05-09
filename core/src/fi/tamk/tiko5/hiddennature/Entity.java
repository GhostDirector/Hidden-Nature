package fi.tamk.tiko5.hiddennature;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.FileTextureData;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

/**
 * Entity. Can be a button or a game object.
 */
public class Entity extends Actor {
    private Texture texture;
    private int action;
    private float scale;
    private int buttonID;
    private boolean isButton;
    private Texture original, pressed;
    private boolean found;
    private ParallelAction up;
    private SequenceAction middleAndUp;
    private MoveToAction moveUp, moveMiddle, slideDown;
    private RotateToAction rotateAction;
    private String path;

    /**
     * Instantiates a new Entity for game objects.
     *
     * @param file     the texture of entity
     * @param x        the x coordinate
     * @param y        the y coordinate
     * @param buttonID the button id that is returned when object is touched
     * @param scale    the scale of entity
     * @param found    if object is found or not
     */
    public Entity(Texture file, float x, float y, int buttonID, float scale, boolean found) {
        path = ((FileTextureData)file.getTextureData()).getFileHandle().path();
        this.scale = scale;
        this.found = found;
        texture = file;
        setBounds(x, y, texture.getWidth() * scale, texture.getHeight() * scale);
        this.buttonID = buttonID;
        objectAnimations();

        addListener(new ActorGestureListener() {

            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
                if(count >= 2){
                    if (!Entity.this.found) {
                        Entity.this.addAction(middleAndUp);
                        Entity.this.action = Entity.this.buttonID;
                    }
                }
            }
        });


    }

    /**
     * Instantiates a new Entity for menu buttons.
     *
     * @param file        the texture of entity
     * @param filePressed the pressed texture of entity
     * @param x           the x coordinate
     * @param y           the y coordinate
     * @param buttonID    the button id that is returned when object is touched
     * @param isbutton    if this is a button or not
     * @param scale       the scale of entity
     */
    public Entity(Texture file, Texture filePressed, float x, float y , int buttonID, boolean isbutton, float scale) {
        found = false;
        original = file;
        pressed = filePressed;
        texture = file;
        this.isButton = isbutton;
        this.buttonID = buttonID;
        setBounds(x, y, texture.getWidth() * scale, texture.getHeight() * scale);

        addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {

                pressedTexture();

                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                releasedTexture();
                action = ((Entity)event.getTarget()).buttonID;
            }
        });
    }

    /**
     * Instantiates a new Entity for text boxes.
     *
     * @param file     the texture of entity
     * @param x        the x coordinate
     * @param y        the y coordinate
     * @param buttonID the button id that is returned when object is touched
     * @param isbutton if this is a button or not
     * @param scale    the scale of entity
     */
    public Entity(Texture file, float x, float y , int buttonID, boolean isbutton, float scale) {
        found = false;
        original = file;
        texture = file;
        this.isButton = isbutton;
        this.buttonID = buttonID;
        setBounds(x, y, texture.getWidth() * scale, texture.getHeight() * scale);
        sliderAnimations();

        addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {

                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button){

                action = ((Entity)event.getTarget()).buttonID;
            }
        });
    }

    /**
     * Provides path of texture for Prefhandler.
     *
     * @return path of texture
     */
    public String getPath(){
        return path;
    }

    @Override
    public void draw(Batch batch, float alpha) {
        batch.draw(texture,
                this.getX(), this.getY(),
                this.getOriginX(),
                this.getOriginY(),
                this.getWidth(),
                this.getHeight(),
                this.getScaleX(),
                this.getScaleY(),
                this.getRotation(),0,0,
                texture.getWidth(), texture.getHeight(), false, false);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

    }

    /**
     * Creates object animations.
     */
    public void objectAnimations() {
        moveUp = new MoveToAction();
        moveMiddle = new MoveToAction();
        slideDown = new MoveToAction();
        rotateAction = new RotateToAction();
        up = new ParallelAction();
        middleAndUp = new SequenceAction();

        moveUp.setPosition(400, 700);
        moveUp.setDuration(2f);
        rotateAction.setRotation(360f);
        rotateAction.setDuration(2f);

        up.addAction(moveUp);
        up.addAction(rotateAction);

        moveMiddle.setPosition((800 - this.getWidth()) / 2, (480 - this.getHeight()) / 2);
        moveMiddle.setDuration(1f);

        middleAndUp.addAction(moveMiddle);
        middleAndUp.addAction(up);
    }

    /**
     * Creates slider animations.
     */
    public void sliderAnimations() {
        slideDown = new MoveToAction();

        slideDown.setPosition(this.getX(), 0f);
        slideDown.setDuration(30f);
    }

    /**
     * Activate slide animation.
     */
    public void slide() {
        this.addAction(slideDown);
    }

    /**
     * Is object found boolean.
     *
     * @return the boolean
     */
    public boolean isFound() {
        return found;
    }

    /**
     * Sets object found boolean.
     *
     * @param found the found
     */
    public void setFound(boolean found) {
        this.found = found;
    }

    /**
     * Pressed button texture.
     */
    public void pressedTexture(){
        texture = pressed;
    }

    /**
     * Released button texture.
     */
    public void releasedTexture(){
        if (isButton) {
            texture = original;
        }
    }

    /**
     * Get unique value assigned to this button. Only if button has been touched.
     *
     * @return unique value assigned to this button.
     */
    public int getAction(){
        return action;
    }

    /**
     * Reset unique value assigned to this button.
     */
    public void resetAction(){
        if (action != 0) {
            this.action = 0;
        }
    }

    /**
     * Gets object scale.
     *
     * @return the scale
     */
    public float getScale() {
        return scale;
    }

    /**
     * Get unique value assigned to this button.
     *
     * @return unique value assigned to this button.
     */
    public int getButtonID(){
        return  buttonID;
    }
}
