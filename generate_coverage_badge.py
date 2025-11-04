#!/usr/bin/env python3
"""
Generate a JaCoCo coverage badge in SVG format.

Parses the JaCoCo XML report, extracts total line coverage,
and creates a simple badge (similar to Shields.io style).
"""

import xml.etree.ElementTree as ET

# Input and output paths
xml_file = "target/site/jacoco/jacoco.xml"
badge_file = "images/coverage-badge.svg"

# Parse the XML report
tree = ET.parse(xml_file)
root = tree.getroot()

# Get the last <counter type="LINE"> — this is the total coverage summary
counter = root.findall(".//counter[@type='LINE']")[-1]
covered = int(counter.attrib["covered"])
missed = int(counter.attrib["missed"])
coverage_percent = int((covered / (covered + missed)) * 100)

# Set color based on thresholds
color = "green" if coverage_percent > 80 else "yellow" if coverage_percent > 50 else "red"

# Generate badge SVG (120x20 px)
svg = f"""<svg xmlns="http://www.w3.org/2000/svg" width="120" height="20">
  <rect width="120" height="20" fill="#555"/>
  <rect x="60" width="60" height="20" fill="{color}"/>
  <text x="28" y="14" fill="#fff" font-family="Verdana" font-size="11">coverage</text>
  <text x="90" y="14" fill="#fff" font-family="Verdana" font-size="11">{coverage_percent}%</text>
</svg>"""

# Write SVG file
with open(badge_file, "w") as f:
    f.write(svg)

print(f"Coverage badge generated: {coverage_percent}% → {badge_file}")
