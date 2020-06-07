using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Globalization;
using Newtonsoft.Json;
using Newtonsoft.Json.Converters;

namespace WindowAttacher
{

    public partial class ChampionPicksAndChampionSelect
    {
        [JsonProperty("championSelect")]
        public ChampionSelect ChampionSelect { get; set; }

        [JsonProperty("picks")]
        public List<Pick> Picks { get; set; }
    }

    public partial class ChampionSelect
    {
        [JsonProperty("champsForPosition")]
        public List<string> ChampsForPosition { get; set; }

        [JsonProperty("compositionCounters")]
        public List<string> CompositionCounters { get; set; }

        [JsonProperty("compositionCountersAdvice")]
        public List<string> CompositionCountersAdvice { get; set; }

        [JsonProperty("enemyTeamComposition")]
        public List<string> EnemyTeamComposition { get; set; }

        [JsonProperty("myTeamComposition")]
        public List<string> MyTeamComposition { get; set; }

        [JsonProperty("enemyTeamStrongPoint")]
        public string EnemyTeamStrongPoint { get; set; }

        [JsonProperty("myTeamStrongPoint")]
        public string MyTeamStrongPoint { get; set; }

        [JsonProperty("enemyTopPicked")]
        public bool EnemyTopPicked { get; set; }

        [JsonProperty("enemyJunglePicked")]
        public bool EnemyJunglePicked { get; set; }

        [JsonProperty("enemyMidPicked")]
        public bool EnemyMidPicked { get; set; }

        [JsonProperty("enemyBotPicked")]
        public bool EnemyBotPicked { get; set; }

        [JsonProperty("enemySupportPicked")]
        public bool EnemySupportPicked { get; set; }

        [JsonProperty("myTopPicked")]
        public bool MyTopPicked { get; set; }

        [JsonProperty("myJunglePicked")]
        public bool MyJunglePicked { get; set; }

        [JsonProperty("myMidPicked")]
        public bool MyMidPicked { get; set; }

        [JsonProperty("myBotPicked")]
        public bool MyBotPicked { get; set; }

        [JsonProperty("mySupportPicked")]
        public bool MySupportPicked { get; set; }

        [JsonProperty("myPosition")]
        public string MyPosition { get; set; }
    }

    public partial class Pick
    {
        [JsonProperty("champion")]
        public string Champion { get; set; }

        [JsonProperty("counterPicks")]
        public List<string> CounterPicks { get; set; }

        [JsonProperty("classes")]
        public List<string> Classes { get; set; }

        [JsonProperty("position")]
        public string Position { get; set; }

        [JsonProperty("strongPoint")]
        public string StrongPoint { get; set; }

        [JsonProperty("myPick")]
        public bool MyPick { get; set; }

        [JsonProperty("myTeam")]
        public bool MyTeam { get; set; }
    }

}
