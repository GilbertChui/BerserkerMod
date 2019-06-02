package theberserker.cards;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.OfferingEffect;
import basemod.abstracts.CustomCard;
import theberserker.BerserkerMod;
import theberserker.patches.AbstractCardEnum;

public class Ritual extends CustomCard {
  static final String ID = "theBerserker:Ritual";
  public static final CardStrings cardString = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardString.NAME;
  public static final String DESCRIPTION = cardString.DESCRIPTION;
  public static final String UPGRADE_DESCRIPTION = cardString.UPGRADE_DESCRIPTION;
  public static final int COST = 0;
  public static final int SELF_DMG = 10;
  public static final int CARDDRAW = 1;
  public static final int ENERGYGAIN = 1;


  public Ritual() {
    super(ID, NAME, BerserkerMod.PLACEHOLDER_ART, COST, DESCRIPTION, AbstractCard.CardType.SKILL,
        AbstractCardEnum.ORANGE, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF);
    this.baseMagicNumber = this.magicNumber = SELF_DMG;
    this.exhaust = true;

  }

  @Override
  public void use(AbstractPlayer p, AbstractMonster m) {
    
    AbstractDungeon.actionManager.addToBottom(new VFXAction(new OfferingEffect(), 0.5F));
    AbstractDungeon.actionManager.addToBottom(new LoseHPAction(p, p, this.magicNumber));
    AbstractDungeon.player.increaseMaxHp(5, true);
    
    if (this.upgraded) {
      AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, CARDDRAW));
      AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(ENERGYGAIN));
    }
  }


  @Override
  public AbstractCard makeCopy() {
    return new Ritual();
  }

  @Override
  public void upgrade() {
    if (!this.upgraded) {
      this.upgradeName();
      this.rawDescription = UPGRADE_DESCRIPTION;
      initializeDescription();

    }
  }
}
