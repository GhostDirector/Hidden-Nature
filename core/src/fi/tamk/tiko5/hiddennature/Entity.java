package fi.tamk.tiko5.hiddennature;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;


public class Entity extends Actor {
    private Texture texture;
    private ScaleToAction scaleDown;
    private ScaleToAction scaleUp;
    private SequenceAction animation;
    private int action;
    private int buttonID;

    public Entity(String file, float x, float y , int buttonID) {
        texture = new Texture(Gdx.files.internal(file));
        setBounds(x, y, texture.getWidth(), texture.getHeight());
        this.buttonID = buttonID;

        addListener(new InputListener() {

            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                action = ((Entity)event.getTarget()).buttonID;


                scaleDown = new ScaleToAction();
                scaleDown.setScale(0.5f);
                scaleDown.setDuration(1f);

                scaleUp = new ScaleToAction();
                scaleUp.setScale(1f);
                scaleUp.setDuration(1f);

                animation = new SequenceAction();
                animation.addAction(scaleDown);
                animation.addAction(scaleUp);

                Entity.this.addAction(animation);

                return true;
            }
        });
    }

    public int getAction(){
        return action;
    }
    public void resetAction(){
        if (action != 0) {
            this.action = 0;
        }
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
}
