package com.bug.freetype;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.utils.ScreenUtils;

public class FreeTypeBugReportGame extends ApplicationAdapter {
    SpriteBatch batch;
    FreeTypeFontGenerator freeTypeFontGenerator;
    BitmapFont bitmapFont;
    int fontSize = 11;

    @Override
    public void create() {
        batch = new SpriteBatch();

        recreateFont();
    }

    private void recreateFont() {
        if (bitmapFont != null) {
            bitmapFont.dispose();
        }
        if (freeTypeFontGenerator != null) {
            freeTypeFontGenerator.dispose();
        }
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = fontSize;
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = 1;
        parameter.incremental = true;
        freeTypeFontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("LanaPixel.ttf"));
        bitmapFont = freeTypeFontGenerator.generateFont(parameter);
    }

    @Override
    public void render() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            fontSize--;
            recreateFont();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            fontSize++;
            recreateFont();
        }

        ScreenUtils.clear(1, 0, 0, 1);
        batch.begin();
        bitmapFont.getData().setScale(1f);
        bitmapFont.draw(batch, "Hallo Welt! Size: " + fontSize, 20, 20);
        bitmapFont.getData().setScale(2f);
        bitmapFont.draw(batch, "Hallo Welt! Size: " + fontSize, 20, 60);
        bitmapFont.getData().setScale(3f);
        bitmapFont.draw(batch, "Hallo Welt! Size: " + fontSize, 20, 120);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        bitmapFont.dispose();
        freeTypeFontGenerator.dispose();
    }
}
