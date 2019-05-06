package theberserker.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Wound;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.RegenPower;
import basemod.abstracts.CustomCard;
import theberserker.BerserkerMod;
import theberserker.patches.AbstractCardEnum;

public class QuickFix extends CustomCard{
  public static final String ID = "QuickFix";
  private static final CardStrings cardString = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardString.NAME;
  public static final String DESCRIPTION = cardString.DESCRIPTION;
  public static final String UPGRADED_DESCRIPTION = cardString.UPGRADE_DESCRIPTION;
  private static final int COST = 1;
  private static final int REGEN_AMT = 3;
  private static final int WOUND = 2;

  public QuickFix() {
    super(ID, NAME, BerserkerMod.PLACEHOLDER_ART, COST, DESCRIPTION, AbstractCard.CardType.SKILL,
        AbstractCardEnum.ORANGE, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
    this.magicNumber = this.baseMagicNumber = REGEN_AMT;

  }

  @Override
  public void upgrade() {
    if (!this.upgraded) {
      this.upgradeName();
      this.rawDescription = UPGRADED_DESCRIPTION;
      initializeDescription();
      
    }
  }

  @Override
  public void use(AbstractPlayer p, AbstractMonster m) {
    AbstractDungeon.actionManager
        .addToBottom(new ApplyPowerAction(p, p, new RegenPower(p, magicNumber), this.magicNumber));
    if (!this.upgraded) {
      AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new Wound(), WOUND, true, true));
    }
  }

  @Override
  public AbstractCard makeCopy() {
    return new QuickFix();
  }
}
