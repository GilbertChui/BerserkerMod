package theberserker.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import basemod.abstracts.CustomCard;
import theberserker.BerserkerMod;
import theberserker.patches.AbstractCardEnum;

public class AxeAQuestion extends CustomCard {
  public static final String ID = "theBerserker:AxeAQuestion";
  public static final CardStrings cardString = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardString.NAME;
  private static final int COST = 1;
  private static final int DEBUFF_AMT = 2;
  private static final int UPGRADE_DEBUFF = 1;
  private static final String DESCRIPTION = cardString.DESCRIPTION;

  public AxeAQuestion() {
    super(ID, NAME, BerserkerMod.PLACEHOLDER_ART, COST, DESCRIPTION, AbstractCard.CardType.SKILL,
        AbstractCardEnum.ORANGE, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY);
    this.baseMagicNumber = this.magicNumber = DEBUFF_AMT;
  }

  @Override
  public void use(AbstractPlayer p, AbstractMonster m) {
    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p,
        new VulnerablePower(m, this.magicNumber, false), this.magicNumber));
    AbstractDungeon.actionManager.addToBottom(
        new ApplyPowerAction(m, p, new WeakPower(m, this.magicNumber, true), this.magicNumber));
  }

  @Override
  public void upgrade() {
    if (!this.upgraded) {
      this.upgradeName();
      this.upgradeMagicNumber(UPGRADE_DEBUFF);
    }

  }

  @Override
  public AbstractCard makeCopy() {
    return new AxeAQuestion();
  }
}
