package com.witcher.witcher_api.model.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.witcher.witcher_api.utils.TableReader;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "character")
public class Character {

    @Id
    private Long id;

    @NotBlank
    private String name;
    @NotBlank
    private String profession;
    @NotBlank
    private String race;
    @NotBlank
    private String gender;

    private Integer age;


    private Integer intelligence;

    private Integer ref;

    private Integer dex;

    private Integer body;

    private Integer spd;

    private Integer emp;

    private Integer cra;

    private Integer will;

    private Integer luck;
    @Transient
    private Integer stun;
    @Transient
    private Integer run;
    @Transient
    private Integer leap;

    private Integer hp;
    @Transient
    private Integer sta;
    @Transient
    private Integer enc;
    @Transient
    private Integer rec;

    @Transient
    private Integer max_hp;
    @Transient
    private Integer melee_bonus;

   private Integer punch ;
    private Integer kick ;

    @OneToOne(mappedBy = "character", cascade = CascadeType.ALL)
    private IntelligenceSkill intelligenceSkill;
    @OneToOne(mappedBy = "character", cascade = CascadeType.ALL)
    private BodySkill bodySkill;
    @OneToOne(mappedBy = "character", cascade = CascadeType.ALL)
    private DexteritySkill dexteritySkill;
    @OneToOne(mappedBy = "character", cascade = CascadeType.ALL)
    private EmpathySkill empathySkill;
    @OneToOne(mappedBy = "character", cascade = CascadeType.ALL)
    private CraftSkill craftSkill;
    @OneToOne(mappedBy = "character", cascade = CascadeType.ALL)
    private WillSkill willSkill;
    @OneToOne(mappedBy = "character", cascade = CascadeType.ALL)
    private ReflexSkill reflexSkill;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToOne
    @JoinColumn(name="l_arm")
    private Weapon l_arm;

    @OneToOne
    @JoinColumn(name="r_arm")
    private Weapon r_arm;

    @OneToOne
    @JoinColumn(name="head")
    private Armor head;

    @OneToOne
    @JoinColumn(name="torso")
    private Armor torso;

    @OneToOne
    @JoinColumn(name="leg")
    private Armor leg;

    public void initializeStats(){
        // TODO set up all the calculated stat from a table
        TableReader reader = new TableReader();
        int keyCalc = (int) Math.floor ((this.body + this.will) / 2);
        this.setMax_hp(reader.getPhysicalStatistics(keyCalc, "hp"));
        this.setSta(reader.getPhysicalStatistics(keyCalc, "sta"));
        this.setRec(reader.getPhysicalStatistics(keyCalc, "rec"));
        this.setStun(reader.getPhysicalStatistics(keyCalc, "stun"));
        //MELEE BONUIS
        this.setMelee_bonus(reader.getMeeleBonus(this.body));
        //RUN (SPD X 3)
        this.setRun(this.spd*3);
        //LEAP
        this.setLeap((int) Math.floor (this.run / 5));
        //ENC
        this.setEnc(this.body * 10);
    }

//    public int rollAbillity(String attrKey){
//        //TODO Implement later...
//        try {
//            Field field = getClass().getDeclaredField(attrKey);
//            field.setAccessible(true);
//            Random random = new Random();
//            int rolledNumber = random.nextInt(10) + 1;
//            System.out.println("INENTÖLLL");
//            System.out.println(this.getAthletic());
//            int total = (int) field.get(this) + rolledNumber + skillModifyerCalculator(attrKey);
//            return total;
//        } catch (NoSuchFieldException | IllegalAccessException e) {
//            e.printStackTrace();
//            return 0;
//        }
//
//    }

//    TODO Part of the roll system. Refactoring needed
//    private int skillModifyerCalculator(String attrKey){
//        List<String> intelligence = List.of("awareness",
//                "business",
//                "deduction",
//                "education",
//                "commonSpeech",
//                "elderSpeech",
//                "dwarvenSpeech",
//                "monsterLore",
//                "socialEtiquette",
//                "streetwise",
//                "tactics",
//                "teaching",
//                "wildernessSurvival");
//
//        List<String> ref = List.of("brawling",
//                "dodge",
//                "melee",
//                "riding",
//                "sailing",
//                "smallBlades",
//                "staff",
//                "swordsmanship");
//
//        List<String> dex = List.of(    "archery",
//                "athletic",
//                "crossbow",
//                "sleightOfHand",
//                "stealth");
//        List<String> body = List.of(      "physique",
//                "endurance");
//        List<String> emp = List.of(  "charisma",
//                "deceit",
//                "fineArt",
//                "gambling",
//                "style",
//                "humanPerception",
//                "leadership",
//                "persuasion",
//                "performance",
//                "seduction");
//        List<String> craft = List.of(    "alchemy",
//                "crafting",
//                "disguise",
//                "firstAid",
//                "forgery",
//                "pickLock",
//                "trapCrafting");
//        List<String> will = List.of(  "courage",
//                "hexWeaving",
//                "intimidation",
//                "spellCasting",
//                "resistMagic",
//                "resistCoercion",
//                "ritualCrafting");
//
//        if(intelligence.contains(attrKey)){
//            return this.intelligence;
//        }
//        if(ref.contains(attrKey)){
//            return this.ref;
//        }
//        if(dex.contains(attrKey)){
//            return this.dex;
//        }
//        if(body.contains(attrKey)){
//            return this.body;
//        }
//        if(emp.contains(attrKey)){
//            return this.emp;
//        }
//        if(craft.contains(attrKey)){
//            return this.cra;
//        }
//        if(will.contains(attrKey)){
//            return this.will;
//        }
//        return 0;
//
//    }


    @Override
    public String toString() {
        return "Character{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", profession='" + profession + '\'' +
                ", race='" + race + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", intelligence=" + intelligence +
                ", ref=" + ref +
                ", dex=" + dex +
                ", body=" + body +
                ", spd=" + spd +
                ", emp=" + emp +
                ", cra=" + cra +
                ", will=" + will +
                ", luck=" + luck +
                ", stun=" + stun +
                ", run=" + run +
                ", leap=" + leap +
                ", hp=" + hp +
                ", sta=" + sta +
                ", enc=" + enc +
                ", rec=" + rec +
                ", max_hp=" + max_hp +
                ", melee_bonus=" + melee_bonus +
                ", punch=" + punch +
                ", kick=" + kick +
                ", bodySkill=" + bodySkill +
                ", user=" + user +
                ", reflexSkill=" + reflexSkill +
                '}';
    }
}
