package theberserker.cards;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Wound;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.VerticalImpactEffect;
import basemod.abstracts.CustomCard;
import theberserker.BerserkerMod;
import theberserker.patches.AbstractCardEnum;

public class DoubleEdge extends CustomCard {
  public static final String ID = "theBerserker:DoubleEdge";
  private static final CardStrings cardString = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardString.NAME;
  public static final String DESCRIPTION = cardString.DESCRIPTION;
  private static final int COST = 1;
  private static final int HEALTHLOST = 5;
  private static final int WOUNDS = 2;
  private static final int DAMAGE = 20;


  public DoubleEdge() {
    super(ID, NAME, BerserkerMod.PLACEHOLDER_ART, COST, DESCRIPTION,
        AbstractCard.CardType.SKILL, AbstractCardEnum.ORANGE, AbstractCard.CardRarity.UNCOMMON,
        AbstractCard.CardTarget.ENEMY);

    this.baseMagicNumber = this.magicNumber = HEALTHLOST;

    this.baseDamage = this.damage = DAMAGE;

  }

  @Override
  public void upgrade() {
    if (!this.upgraded) {
      this.upgradeName();
      this.upgradeMagicNumber(-5);
      this.upgradeDamage(5);
    }
  }

  @Override
  public void use(AbstractPlayer p, AbstractMonster m) {

    AbstractDungeon.actionManager
        .addToBottom(new MakeTempCardInDiscardAction(new Wound(), WOUNDS));
    AbstractDungeon.actionManager.addToBottom(new LoseHPAction(p, p, this.magicNumber));

    if (m != null) {
      AbstractDungeon.actionManager.addToBottom(new VFXAction(
          new VerticalImpactEffect(m.hb.cX + m.hb.width / 4.0F, m.hb.cY - m.hb.height / 4.0F)));
    }

    AbstractDungeon.actionManager
        .addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn),
            AbstractGameAction.AttackEffect.NONE));



  }
}
