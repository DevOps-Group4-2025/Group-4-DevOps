import xml.etree.ElementTree as ET

# Path to JaCoCo XML report
xml_file = "target/site/jacoco/jacoco.xml"
badge_file = "images/coverage-badge.svg"

tree = ET.parse(xml_file)
root = tree.getroot()

# Extract overall line coverage
counter = root.find(".//counter[@type='LINE']")
covered = int(counter.attrib['covered'])
missed = int(counter.attrib['missed'])
coverage_percent = int((covered / (covered + missed)) * 100)

# Generate Shields.io style SVG badge
color = "green" if coverage_percent > 80 else "yellow" if coverage_percent > 50 else "red"
svg = f"""<svg xmlns="http://www.w3.org/2000/svg" width="120" height="20">
  <rect width="120" height="20" fill="#555"/>
  <rect x="60" width="60" height="20" fill="{color}"/>
  <text x="30" y="14" fill="#fff" font-family="Verdana" font-size="11">coverage</text>
  <text x="90" y="14" fill="#fff" font-family="Verdana" font-size="11">{coverage_percent}%</text>
</svg>"""

# Save badge
with open(badge_file, "w") as f:
    f.write(svg)

print(f"Coverage badge generated: {coverage_percent}%")
