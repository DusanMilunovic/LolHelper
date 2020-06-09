namespace HeroPickerFront
{
    using System;
    using System.Collections.Generic;

    using System.Globalization;
    using Newtonsoft.Json;
    using Newtonsoft.Json.Converters;

    public partial class CompositionRule
    {
        [JsonProperty("composition")]
        public long Composition { get; set; }

        [JsonProperty("adChampions")]
        public long AdChampions { get; set; }

        [JsonProperty("apChampions")]
        public long ApChampions { get; set; }

        [JsonProperty("tankChampions")]
        public long TankChampions { get; set; }

        [JsonProperty("healerChampions")]
        public long HealerChampions { get; set; }

        [JsonProperty("pokeChampions")]
        public long PokeChampions { get; set; }

        [JsonProperty("counters")]
        public List<string> Counters { get; set; }

        public string ToJson()
        {
            string retval = "{\n\t\"composition\": " + Composition + ",\n\t\"adChampions\": " + AdChampions + ",\n\t" +
            "\"apChampions\": " + ApChampions + ",\n\t\"tankChampions\":" + TankChampions + ",\n\t" +
            "\"healerChampions\":" + HealerChampions + ",\n\t\"pokeChampions\":" + PokeChampions + ",\n\t" +
            "\"counters\": [\n\t\t";
            for (int i = 0; i < Counters.Count; i++)
            {
                retval += "\"" + Counters[i] + "\"";
                if (i < Counters.Count - 1)
                {
                    retval += ",\n\t\t";
                }
            }
            retval += "\n\t]\n}";
            return retval;
        }
    }
}
