import yaml
import json

# Load the symbols.yml file
with open("symbols.yml", "r", encoding='utf8') as symbols_file:
    symbols_data = yaml.safe_load(symbols_file)

# Create an empty list to store the providers
providers = []

# Iterate through the symbols in the symbols_data dictionary
for num, symbol in symbols_data["symbols"].items():
    # Create a new provider dictionary
    provider = {
        "type": "bitmap",
        "file": "item/hud/compass/{}.png".format(num),
        "ascent": 10,
        "height": 14,
        "chars": [symbol]
    }
    # Append the provider to the providers list
    providers.append(provider)

# Create the final data dictionary
data = {"providers": providers}

# Write the data to the compass.json file
with open("compass.json", "w", encoding='utf8') as compass_file:
    json.dump(data, compass_file, ensure_ascii=False, indent=4)
