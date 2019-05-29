package theberserker.relics;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import basemod.abstracts.CustomRelic;

public class Rock extends CustomRelic {

  public static final String ID = "theBerserker:Rock";
  public static final Texture IMG = new Texture("img/relics/rock.png");

  public Rock() {
    super(ID, IMG, RelicTier.STARTER, LandingSound.SOLID);

  }

  @Override
  public void atTurnStart() {
    if (AbstractDungeon.player.currentHealth <= AbstractDungeon.player.maxHealth / 2) {
      this.flash();
      AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
    }
  }

  @Override
  public String getUpdatedDescription() {
    return DESCRIPTIONS[0];
  }

  public CustomRelic makeCopy() {
    return new Rock();
  }
}
