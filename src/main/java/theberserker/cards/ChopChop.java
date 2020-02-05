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

public class ChopChop extends CustomCard {
  public static final String ID = "theBerserker:ChopChop";
  private static final CardStrings cardString = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardString.NAME;
  public static final String DESCRIPTION = cardString.DESCRIPTION;
  private static final int COST = 2;


  public ChopChop() {
    super(ID, NAME, BerserkerMod.PLACEHOLDER_ART, COST, DESCRIPTION, AbstractCard.CardType.POWER,
        AbstractCardEnum.ORANGE, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF);
    
  }

  @Override
  public void use(AbstractPlayer p, AbstractMonster m) {


  }

  @Override
  public AbstractCard makeCopy() {
    return new ChopChop();
  }

  @Override
  public void upgrade() {
    if (!this.upgraded) {
      this.upgradeName();


    }
  }
}
