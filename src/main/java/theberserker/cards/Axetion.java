package theberserker.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.RegenPower;
import basemod.abstracts.CustomCard;
import theberserker.BerserkerMod;
import theberserker.patches.AbstractCardEnum;

public class Axetion extends CustomCard {
  static final String ID = "theBerserker:Axetion";
  public static final CardStrings cardString = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardString.NAME;
  public static final String DESCRIPTION = cardString.DESCRIPTION;
  public static final int COST = 1;
  public static final int ORBS = 2;
  public static final int SELF_DMG = 5;
  
  
  public Axetion() {
    super(ID, NAME, BerserkerMod.PLACEHOLDER_ART, COST, DESCRIPTION, AbstractCard.CardType.SKILL,
        AbstractCardEnum.ORANGE, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF);
    this.baseMagicNumber = this.magicNumber = SELF_DMG;
    this.exhaust = true;

  }
  
  @Override
  public void use(AbstractPlayer p, AbstractMonster m) {
    AbstractDungeon.actionManager
    .addToBottom(new ApplyPowerAction(p, p, new RegenPower(p, ORBS), ORBS));
    AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(ORBS));
    AbstractDungeon.actionManager
    .addToBottom(new LoseHPAction(p, p, this.magicNumber));
  }
  
  @Override
  public AbstractCard makeCopy() {
    return new Axetion();
  }
  
  @Override
  public void upgrade() {
    if(!this.upgraded) {
      this.upgradeName();
      this.upgradeMagicNumber(-SELF_DMG);;

    }
  }
}
