package com.witcher.witcher_api.model.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
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

    @NotBlank
    private Integer intelligence;
    @NotBlank
    private Integer ref;
    @NotBlank
    private Integer dex;
    @NotBlank
    private Integer body;
    @NotBlank
    private Integer spd;
    @NotBlank
    private Integer emp;
    @NotBlank
    private Integer cra;
    @NotBlank
    private Integer will;
    @NotBlank
    private Integer luck;
    private Integer stun;
    private Integer run;
    private Integer leap;
    private Integer hp;
    private Integer sta;
    private Integer enc;
    private Integer rec;

    private Integer max_hp;
    private Integer melee_bonus;

   private Integer punch = 0;
    private Integer kick = 0;

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
    private CraftSkill willSkill;
    @OneToOne(mappedBy = "character", cascade = CascadeType.ALL)
    private ReflexSkill reflexSkill;

    //@ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne
    @JsonIgnore
    private User user;



    public void setCalculatedStats(){
        // TODO set up all the calculated stat from a table
//        TableReader reader = new TableReader();
//        int keyCalc = (int) Math.floor ((this.body + this.will) / 2);
//        this.setHp(reader.physicalTableRead(keyCalc, "hp"));
//        this.setSta(reader.physicalTableRead(keyCalc, "sta"));
//        this.setRec(reader.physicalTableRead(keyCalc, "rec"));
//        this.setStun(reader.physicalTableRead(keyCalc, "stun"));
//        //MELEE BONUIS
//        this.setMeleeBonus(reader.meleeBonusRead(this.body));
//        //RUN (SPD X 3)
//        this.setRun(this.spd*3);
//        //LEAP
//        this.setLeap((int) Math.floor (this.run / 5));
//        //ENC
//        this.setEnc(this.body * 10);
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


}
