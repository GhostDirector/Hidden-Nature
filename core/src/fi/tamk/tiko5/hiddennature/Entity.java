package fi.tamk.tiko5.hiddennature;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

public class Entity extends Actor {
    private Texture texture;
    private int action;
    private float scale;
    private int buttonID;
    private boolean isButton;
    private String original, pressed;
    private boolean found;
    private ParallelAction up, down, left, right;
    private MoveToAction moveUp, moveDown, moveLeft, moveRight;
    private RotateToAction rotateAction;

    public Entity(String file, float x, float y, int buttonID, float scale, boolean found) {
        this.scale = scale;
        this.found = found;
        original = file;
        texture = new Texture(Gdx.files.internal(original));
        setBounds(x, y, texture.getWidth() * scale, texture.getHeight() * scale);
        this.buttonID = buttonID;
        moveUp = new MoveToAction();
        moveDown = new MoveToAction();
        moveLeft = new MoveToAction();
        moveRight = new MoveToAction();
        rotateAction = new RotateToAction();
        up = new ParallelAction();
        down = new ParallelAction();
        left = new ParallelAction();
        right = new ParallelAction();
        createAnimations();

        addListener(new ActorGestureListener() {

            @Override
            public boolean longPress(Actor actor, float x, float y) {

                if (!Entity.this.found) {
                    switch (MathUtils.random(1, 4)) {
                        case 1:
                            Entity.this.addAction(up);
                            break;

                        case 2:
                            Entity.this.addAction(down);
                            break;

                        case 3:
                            Entity.this.addAction(left);
                            break;

                        case 4:
                            Entity.this.addAction(right);
                            break;
                    }
                    Entity.this.action = Entity.this.buttonID;
                }
                return true;
            }
        });
    }
    
    public Entity(String file, String filePressed, float x, float y , int buttonID, boolean isbutton, float scale) {
        found = false;
        original = file;
        pressed = filePressed;
        texture = new Texture(Gdx.files.internal(original));
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
    
    public void createAnimations() {
        moveUp.setPosition(this.getX() + 1000, this.getY());
        moveUp.setDuration(3f);
        rotateAction.setRotation(360f);
        rotateAction.setDuration(1f);

        up.addAction(moveUp);
        up.addAction(rotateAction);

        moveDown.setPosition(this.getX() - 1000, this.getY());
        moveDown.setDuration(3f);

        down.addAction(moveDown);
        down.addAction(rotateAction);

        moveLeft.setPosition(this.getX(), this.getY() - 1000);
        moveLeft.setDuration(3f);

        left.addAction(moveLeft);
        left.addAction(rotateAction);

        moveRight.setPosition(this.getX(), this.getY() + 1000);
        moveRight.setDuration(3f);

        right.addAction(moveRight);
        right.addAction(rotateAction);
    }

    public boolean isFound() {
        return found;
    }
    
    public void setFound(boolean found) {
        this.found = found;
    }
    
    public void pressedTexture(){
        texture = new Texture(pressed);
    }
    
    public void releasedTexture(){
        if (isButton) {
            texture = new Texture(original);
        }
    }
    
    public Texture getTexture(){
        return texture;
    }
    
    public int getAction(){
        return action;
    }
    
    public void setAction(int action) {
        this.action = action;
    }
    
    public void resetAction(){
        if (action != 0) {
            this.action = 0;
        }
    }
    
    public float getScale() {
        return scale;
    }
    
    public String getOriginal() {
        return original;
    }
    
    public int getButtonID(){
        return  buttonID;
    }
}
