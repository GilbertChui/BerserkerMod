package theberserker.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import basemod.abstracts.CustomCard;
import theberserker.BerserkerMod;
import theberserker.patches.AbstractCardEnum;

public class JaxPremium extends CustomCard {

  public static final String ID = "theBerserker:JaxPremium";
  private static final CardStrings cardString = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardString.NAME;
  public static final String DESCRIPTION = cardString.DESCRIPTION;
  private static final int COST = 0;
  private static final int PERMANANTHEALTHLOST = 5;
  private static final int STRENGTHGAIN = 3;
  private static final int CARDDRAW = 2;
  private static final int ENERGYGAIN = 1;

  public JaxPremium() {
    super(ID, NAME, BerserkerMod.makeCardPath("jaxpremium.png"), COST, DESCRIPTION,
        AbstractCard.CardType.SKILL, AbstractCardEnum.ORANGE, AbstractCard.CardRarity.UNCOMMON,
        AbstractCard.CardTarget.SELF);
    this.baseMagicNumber = this.magicNumber = STRENGTHGAIN;

  }

  @Override
  public void upgrade() {
    this.upgradeName();
    this.upgradeMagicNumber(2);
  }

  @Override
  public void use(AbstractPlayer p, AbstractMonster m) {
    AbstractDungeon.actionManager.addToBottom(
        new ApplyPowerAction(p, p, new StrengthPower(p, magicNumber), this.magicNumber));

    AbstractDungeon.player.decreaseMaxHealth(PERMANANTHEALTHLOST);

    AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, CARDDRAW));

    AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(ENERGYGAIN));

  }



}
