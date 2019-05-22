package theberserker.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import static theberserker.BerserkerMod.getPlaceholderPowerTexture;;

public class WhirlwindOfDeathPower extends AbstractPower {
  
  // TODO: make PowerStrings.json for this power
  public static final String POWER_ID = "theBerserker:WhirlwindOfDeathPower";
  public static final PowerStrings powerString = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
  public static final String NAME = powerString.NAME;
  public static final String[] DESCRIPTIONS = powerString.DESCRIPTIONS;
 
  public WhirlwindOfDeathPower(final AbstractCreature owner, int amount) {
    this.name = NAME;
    this.ID = POWER_ID;
    this.owner = owner;
    this.amount = amount;
    this.type = AbstractPower.PowerType.BUFF;
    this.img = getPlaceholderPowerTexture();
    this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
  }
  
  public void upgradeDescription() {
    this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
  }
  
  @Override
  public void onPlayCard(AbstractCard card, AbstractMonster m) {
    int cardsPlayed = AbstractDungeon.actionManager.cardsPlayedThisTurn.size() + 1;
    for (int i = 0; i <= cardsPlayed; i++) {
      this.flash();
      AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_HEAVY"));
      AbstractDungeon.actionManager.addToBottom(new VFXAction(this.owner, new CleaveEffect(), 0.1F));
      for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
        AbstractDungeon.actionManager
        .addToBottom(new DamageAction(mo, new DamageInfo(this.owner, this.amount),
            AbstractGameAction.AttackEffect.NONE));
      
      }
    }
    
  }
    
}
