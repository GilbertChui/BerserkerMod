package theberserker.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.RegenPower;
import basemod.abstracts.CustomCard;
import theberserker.BerserkerMod;
import theberserker.patches.AbstractCardEnum;

public class Panic extends CustomCard{
  
  static final String ID = "Panic";
  public static final CardStrings cardString = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardString.NAME;
  public static final String DESCRIPTION = cardString.DESCRIPTION;
  public static final int REGEN_AMT = 20;
  public static final int UPGRADE_AMT = 5;
  public static final int DAZED = 5;
  public static final int COST = 3;
  public static final int UPGRADED_COST = 2;
  
  public Panic() {
    super(ID, NAME, BerserkerMod.PLACEHOLDER_ART, COST, DESCRIPTION, AbstractCard.CardType.POWER,
        AbstractCardEnum.ORANGE, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF);
    this.baseMagicNumber = this.magicNumber = REGEN_AMT;
    this.exhaust = true;
  }
  
  @Override
  public void use(AbstractPlayer p, AbstractMonster m) {
    //TODO: FIGURE OUT HOW TO ADD WOUNDS TO DRAW PILE
    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new RegenPower(p, this.magicNumber), this.magicNumber));
    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new Dazed(), DAZED, true, true));
    //AbstractDungeon.actionManager.addToBottom();
    //TODO: UPDATE ModTheSpire.json after
  }
  
  @Override
  public AbstractCard makeCopy() {
    return new Panic();
  }
  
  @Override
  public void upgrade() {
    if(!this.upgraded) {
      this.upgradeName();
      this.upgradeMagicNumber(UPGRADE_AMT);
      this.upgradeBaseCost(UPGRADED_COST);
    }
  }
}
