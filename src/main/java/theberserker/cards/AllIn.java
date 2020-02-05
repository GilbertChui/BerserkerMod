package theberserker.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Wound;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import basemod.abstracts.CustomCard;
import theberserker.BerserkerMod;
import theberserker.patches.AbstractCardEnum;

public class AllIn extends CustomCard {
  
  public static final String ID = "theBerserker:AllIn";
  private static final CardStrings cardString = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardString.NAME;
  public static final String DESCRIPTION = cardString.DESCRIPTION;
  private static final int UPGRADE_AMT = 5;
  private static final int STRENGTHGAIN = 10;
  private static final int WOUNDS = 10;
  private static final int COST = 2;


  public AllIn() {
    super(ID, NAME, BerserkerMod.PLACEHOLDER_ART, COST, DESCRIPTION, AbstractCard.CardType.POWER,
        AbstractCardEnum.ORANGE, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF);
    this.baseMagicNumber = this.magicNumber = STRENGTHGAIN;
  }

  @Override
  public void use(AbstractPlayer p, AbstractMonster m) {
    AbstractDungeon.actionManager.addToBottom(
        new ApplyPowerAction(p, p, new StrengthPower(p, magicNumber), this.magicNumber));
    for(int i =0; i <= WOUNDS; i++) {
      AbstractDungeon.actionManager
          .addToBottom(new MakeTempCardInDiscardAction(new Wound(), 1));
    }

  }

  @Override
  public AbstractCard makeCopy() {
    return new AllIn();
  }

  @Override
  public void upgrade() {
    if (!this.upgraded) {
      this.upgradeName();
      this.upgradeMagicNumber(UPGRADE_AMT);

    }
  }

}
